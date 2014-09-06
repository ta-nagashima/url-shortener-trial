package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

public interface VolumeChargeEngagementEntity {

    public boolean isNotExist();

    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase();

}
