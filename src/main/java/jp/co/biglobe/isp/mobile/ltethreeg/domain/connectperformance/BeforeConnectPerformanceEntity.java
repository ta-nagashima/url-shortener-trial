package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicyEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;

public interface BeforeConnectPerformanceEntity {

    public LteThreeGEngagementNumber getLteThreeGEngagementNumber();

    public LimitStatus getSpeedLimitStatus();

    public LimitStatus getDestinationLimitStatusForHistory();

    public ConnectControlPolicyEntity getConnectControlPolicyEntity();

    public AfterConnectPerformanceEntity limit72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity limitRelease72Hour(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity limit1Month(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity limitRelease1Month(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity limitDestination(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity limitReleaseDestination(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity planChange(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity volumeChargePurchase(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity volumeChargeStopped(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity volumeChargeStart(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity volumeChargeComplete(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

    public AfterConnectPerformanceEntity speedChargePurchase();

    public AfterConnectPerformanceEntity speedChargeComplete(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity);

}
