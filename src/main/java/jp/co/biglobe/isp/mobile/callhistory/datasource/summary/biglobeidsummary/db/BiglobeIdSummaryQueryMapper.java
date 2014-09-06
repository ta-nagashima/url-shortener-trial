package jp.co.biglobe.isp.mobile.callhistory.datasource.summary.biglobeidsummary.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.BiglobeIdSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public interface BiglobeIdSummaryQueryMapper {

    public List<BiglobeIdSummary> findByUserIdAndTargetMonth(
            @Param("userId") UserId userId,
            @Param("targetMonth") TargetMonth targetMonth,
            @Param("firstDate") Date firstDate,
            @Param("lastDate") Date lastDate
    );


}
