package bjl.interfaces.triratna;

import bjl.application.triratna.ITriratnaAppService;
import bjl.application.triratna.command.ListITriratnaCommand;
import bjl.application.triratna.representation.TriratnaRepresentation;
import bjl.infrastructure.persistence.hibernate.generic.Pagination;
import bjl.interfaces.shared.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangjin on 2018/1/15.
 */
@Controller
@RequestMapping("triratna")
public class TriratnaController extends BaseController{

    @Autowired
    private ITriratnaAppService triratnaAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListITriratnaCommand command){

        return new ModelAndView("/triratna/list","pagination",triratnaAppService.pagination(command))
                .addObject("command",command).addObject("total",triratnaAppService.total(command));

    }
}
