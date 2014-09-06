package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.immediatedisengagementrefer;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 即時解約参照
 */
@ToString(includeFieldNames = false)
public class VoiceImmediateDisengagementReferRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

}
