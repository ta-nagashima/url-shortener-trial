package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicyEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;

public interface AfterConnectPerformanceEntity {

    public LimitStatus getSpeedLimitStatus();

    public LimitStatus getDestinationLimitStatusForHistory();

    public ConnectControlPolicyEntity getConnectControlPolicyEntity();

    public boolean isSameConnectControlPolicy(BeforeConnectPerformanceEntity before);

    public boolean isNoLimitedForVolume();

}
