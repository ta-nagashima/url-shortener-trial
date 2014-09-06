package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchasedVolumeNumber;

public class VolumeChargeFeeFactory {

    private static final Integer VOLUME_CHARGE_ONE_CHARGE_FEE = 300;

    public static VolumeChargeFee create(VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber){
        return new VolumeChargeFee(VOLUME_CHARGE_ONE_CHARGE_FEE * volumeChargePurchasedVolumeNumber.getValue());
    }

    public static String getApiValue(){
        return VOLUME_CHARGE_ONE_CHARGE_FEE.toString();
    }
}
