package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementStartDateForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class VoiceEngagementApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementStartDateForm voiceEngagementStartDateForm;

}
