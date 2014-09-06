package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.mnpoutcompletionanddisengagementapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpOutCompletionConfirmedDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementDisengageOrderDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementEndDateTimeForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class VoiceMnpOutCompletionAndDisengagementApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpOutCompletionConfirmedDateForm mnpOutCompletionConfirmedDateForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementEndDateTimeForm voiceEngagementEndDateTimeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementDisengageOrderDateForm voiceEngagementDisengageOrderDateForm;

}
