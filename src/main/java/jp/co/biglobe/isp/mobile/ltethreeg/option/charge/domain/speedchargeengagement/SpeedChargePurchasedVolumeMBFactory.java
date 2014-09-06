package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

public class SpeedChargePurchasedVolumeMBFactory {

    private static final Integer SPEED_CHARGE_ONE_PURCHASED_VOLUME = 100;

    public static PurchasedVolumeMB create(SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){
        return new PurchasedVolumeMB(SPEED_CHARGE_ONE_PURCHASED_VOLUME * speedChargePurchasedVolumeNumber.getValue());
    }
}
