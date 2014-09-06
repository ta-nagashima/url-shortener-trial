package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;

public interface HistoryListRepository {

    public HistoryList findByUserIdAndMsisdnAndTargetMonth(
            UserId userId, ValidMsisdn validMsisdn,
            TargetMonth targetMonth, Paging paging, CallKind callKind);
}
