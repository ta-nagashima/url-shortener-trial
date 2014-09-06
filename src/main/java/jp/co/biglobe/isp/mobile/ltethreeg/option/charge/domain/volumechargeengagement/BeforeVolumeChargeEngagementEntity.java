package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

public interface BeforeVolumeChargeEngagementEntity extends VolumeChargeEngagementEntity{

    public AfterVolumeChargeEngagementEntity planChange();

    public AfterVolumeChargeEngagementEntity complete();

}
