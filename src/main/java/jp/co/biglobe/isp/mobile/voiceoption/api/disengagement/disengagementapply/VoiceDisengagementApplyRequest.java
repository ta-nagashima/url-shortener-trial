package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementDisengageOrderDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementDisengageReasonForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementEndDateTimeForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 解約予約
 */

@ToString(includeFieldNames = false)
public class VoiceDisengagementApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementEndDateTimeForm voiceEngagementEndDateTimeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementDisengageReasonForm voiceEngagementDisengageReasonForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementDisengageOrderDateForm voiceEngagementDisengageOrderDateForm;

}
