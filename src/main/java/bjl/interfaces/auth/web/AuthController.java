package bjl.interfaces.auth.web;

import bjl.application.account.representation.AccountRepresentation;
import bjl.application.auth.IAuthAppService;
import bjl.application.auth.command.LoginCommand;
import bjl.core.common.Constants;
import bjl.core.exception.AccountException;
import bjl.core.util.CoreHttpUtils;
import bjl.interfaces.shared.web.AlertMessage;
import bjl.interfaces.shared.web.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Author pengyi
 * Date 2016/4/5.
 */
@Controller
public class AuthController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public IAuthAppService authAppService;

    @RequestMapping(value = "/login_hf_889", method = RequestMethod.GET)
    public ModelAndView login(@ModelAttribute("command") LoginCommand command) {
        return new ModelAndView("/login", "command", command);
    }

    @RequestMapping(value = "/login_hf_889", method = RequestMethod.POST)
    public ModelAndView login(@Valid @ModelAttribute("command") LoginCommand command, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/login", "command", command);
        }
        AlertMessage alertMessage;

        boolean flag;

        try {
            flag = command.getVerificationCode() == (int) request.getSession().getAttribute("code");
        } catch (Exception e) {
            e.printStackTrace();
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.verificationCode.Error.messages", null, locale));
            return new ModelAndView("/login", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        if (flag) {
            try {
                String loginIP = CoreHttpUtils.getClientIP(request);
                String loginPlatform = CoreHttpUtils.getLoginPlatform(request);
                command.setLoginIP(loginIP);
                command.setLoginPlatform(loginPlatform);
                AccountRepresentation user = authAppService.login(command);
                Subject subject = SecurityUtils.getSubject();
                if (subject.hasRole("admin")) {
                    logger.info("管理员:【"+subject.getPrincipal() + "】登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/main");
                } else if (subject.hasRole("filipino")) {
                    logger.info("菲律宾厅管理员：【"+subject.getPrincipal() + "】登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/game_bet/list?roomType=1");
                } else if (subject.hasRole("vietnam")) {
                    logger.info("越南厅管理员：【"+subject.getPrincipal() + "】登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/game_bet/list?roomType=2");
                }else if (subject.hasRole("macao")) {
                    logger.info("澳门厅管理员：【"+subject.getPrincipal() + "】登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/game_bet/list?roomType=3");
                }else if (subject.hasRole("firstAgent")||subject.hasRole("secondAgent")) {
                    logger.info("代理：【"+subject.getPrincipal() + "】登录成功！时间:" + new Date());
                    session.setAttribute(Constants.SESSION_USER, user);
                    return new ModelAndView("redirect:/agent");
                } else {//用户没有对应角色 不让登录
                    alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.account.NotPermission.messages", null, locale));
                    redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                    return new ModelAndView("redirect:/login_hf_889");
                }
            } catch (UnknownAccountException ue) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.NotExists.messages", null, locale));
            } catch (AccountException e) {
                logger.warn(e.getMessage());
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.NotPermission.messages", null, locale));
            } catch (IncorrectCredentialsException ie) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.Error.messages", null, locale));
            } catch (LockedAccountException le) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("login.account.Disable.messages", null, locale));
            } catch (Exception e) {
                e.printStackTrace();
                alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                        this.getMessage("login.login.Failure.messages", null, locale));
            }
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/login_hf_889");
        }
        alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING, this.getMessage("login.verificationCode.Error.messages", null, locale));
        return new ModelAndView("/login", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/hf_889");
    }

    /****/
    @RequestMapping("/denied")
    public ModelAndView unauthorized() throws Exception {
        return new ModelAndView("/error/denied");
    }
}
