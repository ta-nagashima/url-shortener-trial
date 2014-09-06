package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

public interface SpeedChargeEngagementEntity {

    public boolean isNotExist();

    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase();

}
