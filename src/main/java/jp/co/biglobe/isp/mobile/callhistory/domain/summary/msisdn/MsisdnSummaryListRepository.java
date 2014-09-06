package jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;

public interface MsisdnSummaryListRepository {

    public MsisdnSummaryList findByUserIdAndTargetMonth(UserId userId, TargetMonth targetMOnth);

}
