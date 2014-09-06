package jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;

public interface BiglobeIdSummaryListRepository {

    public BiglobeIdSummaryList findByUserIdAndTargetMonth(UserId userId, TargetMonth targetMOnth);

}
