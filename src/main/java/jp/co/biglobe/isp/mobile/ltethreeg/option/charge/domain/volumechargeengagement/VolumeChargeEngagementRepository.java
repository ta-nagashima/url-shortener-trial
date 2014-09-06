package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface VolumeChargeEngagementRepository {

    public ValidVolumeChargeEngagementEntity create(LteThreeGEngagementNumber lteThreeGEngagementNumber,
                                               VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber);

    public void purchase(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity);

    public void complete(AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity);

    public void completePlanChange(AfterVolumeChargeEngagementEntity afterVolumeChargeEngagementEntity);

    public VolumeChargeEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ValidVolumeChargeEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

}
