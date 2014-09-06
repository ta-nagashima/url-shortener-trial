package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutconfirmfordisengagerefer;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

/**
 * MNP転出参照
 */
public class MnpOutConfirmForDisengageReferRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;
}
