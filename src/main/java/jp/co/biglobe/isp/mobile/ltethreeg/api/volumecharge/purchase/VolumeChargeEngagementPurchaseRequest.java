package jp.co.biglobe.isp.mobile.ltethreeg.api.volumecharge.purchase;

import jp.co.biglobe.isp.mobile.ltethreeg.api.form.volumecharge.VolumeChargePurchasedVolumeNumberForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class VolumeChargeEngagementPurchaseRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VolumeChargePurchasedVolumeNumberForm volumeChargePurchasedVolumeNumberForm;

}
