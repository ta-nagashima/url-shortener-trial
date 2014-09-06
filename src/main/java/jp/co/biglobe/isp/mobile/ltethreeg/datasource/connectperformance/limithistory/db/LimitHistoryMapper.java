package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.limithistory.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.LimitHistoryForView;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.LimitHistoryTotalCount;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LimitHistoryMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("limitHistory") LimitHistory limitHistory
    );

    public LimitHistoryTotalCount findByLteThreeGEngagementNumberForCount(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public List<LimitHistoryForView> findAllByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("row") Integer row,
            @Param("high") Integer high
    );
}
