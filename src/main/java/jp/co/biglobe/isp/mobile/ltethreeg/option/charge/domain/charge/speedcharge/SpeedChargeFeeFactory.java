package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;

public class SpeedChargeFeeFactory {

    private static final Integer SPEED_CHARGE_ONE_CHARGE_FEE = 300;

    public static SpeedChargeFee create(SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){
        return new SpeedChargeFee(SPEED_CHARGE_ONE_CHARGE_FEE * speedChargePurchasedVolumeNumber.getValue());
    }

    public static String getApiValue(){
        return SPEED_CHARGE_ONE_CHARGE_FEE.toString();
    }
}
