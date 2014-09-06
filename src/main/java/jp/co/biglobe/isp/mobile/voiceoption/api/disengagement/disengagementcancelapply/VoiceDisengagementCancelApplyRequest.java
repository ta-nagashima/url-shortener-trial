package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementcancelapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 解約予約キャンセル
 */

@ToString(includeFieldNames = false)
public class VoiceDisengagementCancelApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

}
