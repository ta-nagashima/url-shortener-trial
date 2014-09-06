package jp.co.biglobe.isp.mobile.callhistory.datasource.summary.msisdnsummary.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.MsisdnSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface MsisdnSummaryQueryMapper {

    public List<MsisdnSummary> findByUserIdAndTargetMonth(
            @Param("userId") UserId userId,
            @Param("targetMonth") TargetMonth targetMonth,
            @Param("firstDate") Date firstDate,
            @Param("lastDate") Date lastDate
    );


}
