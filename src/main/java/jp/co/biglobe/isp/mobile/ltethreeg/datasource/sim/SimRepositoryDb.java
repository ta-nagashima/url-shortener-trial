package jp.co.biglobe.isp.mobile.ltethreeg.datasource.sim;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.sim.db.SimRepositoryQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.SimRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SimRepositoryDb implements SimRepository {

    @Autowired
    private SimRepositoryQueryMapper simRepositoryQueryMapper;

    @Override
    public ValidSimList findByLteThreeGEngagementNumberUnderValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        List<ValidSimEntity> validSimEntityList =
            simRepositoryQueryMapper.findByLteThreeGEngagementNumberUnderValid(lteThreeGEngagementNumber, new Date());

        return new ValidSimList(validSimEntityList);
    }

    @Override
    public ValidSimList findByUserIdAndTargetMonthUnderValid(UserId userId, TargetMonth targetMonth) {
        List<ValidSimEntity> validSimEntityList =
                simRepositoryQueryMapper.findByUserIdAndTargetMonthUnderValid(userId, targetMonth);

        return new ValidSimList(validSimEntityList);
    }

}
