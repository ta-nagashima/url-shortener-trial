package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;

public class LimitHistoryFactory {


    public static LimitHistory create1MonthLimit(
            BeforeConnectPerformanceEntity before,
            AfterConnectPerformanceEntity after){

        return create(before, after, ConnectPerformanceEvent.LIMIT_1_MONTH);
    }

    public static LimitHistory create(BeforeConnectPerformanceEntity before, AfterConnectPerformanceEntity after, ConnectPerformanceEvent connectPerformanceEvent){

        return new LimitHistory(
                before.getLteThreeGEngagementNumber(),
                before.getSpeedLimitStatus(),
                after.getSpeedLimitStatus(),
                before.getDestinationLimitStatusForHistory(),
                after.getDestinationLimitStatusForHistory(),
                before.getConnectControlPolicyEntity().getConnectControlPolicy(),
                after.getConnectControlPolicyEntity().getConnectControlPolicy(),
                LimitHistoryRequestDateTime.create(),
                connectPerformanceEvent
        );

    }
}
