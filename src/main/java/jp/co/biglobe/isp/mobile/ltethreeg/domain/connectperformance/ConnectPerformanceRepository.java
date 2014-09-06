package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface ConnectPerformanceRepository {

    public ValidConnectPerformanceEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ConnectPerformanceEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public void insert(ValidConnectPerformanceEntity validConnectPerformanceEntity);

    public void insertMaybe(ValidConnectPerformanceEntity validConnectPerformanceEntity);

    public void update(BeforeConnectPerformanceEntity before, AfterConnectPerformanceEntity after, ConnectPerformanceEvent connectPerformanceEvent);

}
