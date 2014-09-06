package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LimitHistory {

    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final SpeedLimitStatusForHistory beforeSpeedLimitStatus;

    @Getter
    private final SpeedLimitStatusForHistory afterSpeedLimitStatus;

    @Getter
    private final DestinationLimitStatusForHistory beforeDestinationLimitStatus;

    @Getter
    private final DestinationLimitStatusForHistory afterDestinationLimitStatus;

    @Getter
    private final ConnectControlPolicy beforeConnectControlPolicy;

    @Getter
    private final ConnectControlPolicy afterConnectControlPolicy;

    @Getter
    private final LimitHistoryRequestDateTime limitHistoryRequestDateTime;

    @Getter
    private final ConnectPerformanceEvent connectPerformanceEvent;


    public LimitHistory(
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            LimitStatus beforeSpeedLimitStatus,
            LimitStatus afterSpeedLimitStatus,
            LimitStatus beforeDestinationLimitStatus,
            LimitStatus afterDestinationLimitStatus,
            ConnectControlPolicy beforeConnectControlPolicy,
            ConnectControlPolicy afterConnectControlPolicy,
            LimitHistoryRequestDateTime limitHistoryRequestDateTime,
            ConnectPerformanceEvent connectPerformanceEvent
    ){
        this.lteThreeGEngagementNumber = lteThreeGEngagementNumber;
        this.beforeSpeedLimitStatus = beforeSpeedLimitStatus;
        this.afterSpeedLimitStatus = afterSpeedLimitStatus;
        this.beforeDestinationLimitStatus = beforeDestinationLimitStatus;
        this.afterDestinationLimitStatus = afterDestinationLimitStatus;
        this.beforeConnectControlPolicy = beforeConnectControlPolicy;
        this.afterConnectControlPolicy = afterConnectControlPolicy;
        this.limitHistoryRequestDateTime = limitHistoryRequestDateTime;
        this.connectPerformanceEvent = connectPerformanceEvent;

    }
}
