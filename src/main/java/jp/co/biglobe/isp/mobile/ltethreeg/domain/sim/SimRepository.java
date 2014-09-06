package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface SimRepository {

    public ValidSimList findByLteThreeGEngagementNumberUnderValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ValidSimList findByUserIdAndTargetMonthUnderValid(UserId userId, TargetMonth targetMonth);
}
