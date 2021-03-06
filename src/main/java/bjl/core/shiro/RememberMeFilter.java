package bjl.core.shiro;

import bjl.application.account.representation.AccountRepresentation;
import bjl.application.auth.IAuthAppService;
import bjl.application.auth.command.LoginCommand;
import bjl.core.common.Constants;
import bjl.core.util.CoreHttpUtils;
import bjl.core.util.CoreRc4Utils;
import bjl.core.util.CoreStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by pengyi on 2016/4/5 0005.
 */
public class RememberMeFilter implements Filter {

    @Autowired
    private IAuthAppService authAppService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        if (null == session.getAttribute(Constants.SESSION_USER)) {
            if (null != cookies && cookies.length > 0) {
                String cookieStr = CoreStringUtils.EMPTY;

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(Constants.COOKIE_USER)) {
                        cookieStr = CoreRc4Utils.decry_RC4(cookie.getValue(), Constants.PASSWORD_ENCRYP_KEY);
                        break;
                    }
                }
                if (!CoreStringUtils.isEmpty(cookieStr)) {
                    String[] arr = cookieStr.split(",");
                    LoginCommand command = new LoginCommand();
                    command.setLoginIP(CoreHttpUtils.getClientIP(request));
                    command.setUserName(arr[0]);
                    command.setPassword(arr[1]);
                    command.setRememberMe(true);
                    try {
                        AccountRepresentation user = authAppService.login(command);
                        session = request.getSession(false);
                        session.setAttribute(Constants.SESSION_USER, user);
                        CoreHttpUtils.writeCookie(command, request, response);
                    } catch (Exception e) {
                        CoreHttpUtils.clearCookie(request, response);
                        response.sendRedirect("/");
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
