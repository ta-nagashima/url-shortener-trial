package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface SpeedChargeEngagementRepository {

    public ValidSpeedChargeEngagementEntity create(LteThreeGEngagementNumber lteThreeGEngagementNumber, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber);

    public void insert(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity);

    public void planChange(AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity);

    public void complete(AfterSpeedChargeEngagementEntity afterSpeedChargeEngagementEntity);

    public SpeedChargeEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ValidSpeedChargeEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

}
