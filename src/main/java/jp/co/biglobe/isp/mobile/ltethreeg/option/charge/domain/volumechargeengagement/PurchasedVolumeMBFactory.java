package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

public class PurchasedVolumeMBFactory {

    private static final Integer VOLUME_CHARGE_ONE_PURCHASED_VOLUME = 100;

    public static PurchasedVolumeMB create(VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber){
        return new PurchasedVolumeMB(VOLUME_CHARGE_ONE_PURCHASED_VOLUME * volumeChargePurchasedVolumeNumber.getValue());
    }
}
