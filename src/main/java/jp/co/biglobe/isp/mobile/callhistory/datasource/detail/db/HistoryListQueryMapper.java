package jp.co.biglobe.isp.mobile.callhistory.datasource.detail.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.History;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryListQueryMapper {

    public List<History> findByUserIdAndMsisdnAndTargetMonth(
            @Param("userId") UserId userId,
            @Param("msisdn") ValidMsisdn validMsisdn,
            @Param("targetMonth") TargetMonth targetMonth,
            @Param("low") Integer low,
            @Param("high") Integer high,
            @Param("internationalCallKind") CallKind callKind
    );


}
