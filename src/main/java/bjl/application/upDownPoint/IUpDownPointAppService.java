package bjl.application.upDownPoint;


import bjl.application.upDownPoint.command.UpDownPointCommand;
import bjl.application.userManager.command.ModifyUserCommand;
import bjl.domain.model.upDownPoint.UpDownPoint;
import bjl.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by dyp on 2017-12-20.
 */
public interface IUpDownPointAppService {

    void create(ModifyUserCommand command);

   Pagination<UpDownPoint> pagination(UpDownPointCommand command);

    Map<String, BigDecimal> sum(Date date);

}
