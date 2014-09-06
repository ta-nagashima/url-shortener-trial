package jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.purchase;

import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.speedcharge.SpeedChargePurchasedVolumeNumberForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class SpeedChargeEngagementPurchaseRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SpeedChargePurchasedVolumeNumberForm speedChargePurchasedVolumeNumberForm;

}
