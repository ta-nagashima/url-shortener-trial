package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.mnpoutcompletionandnewordercancel;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpOutCompletionConfirmedDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementCancelOrderDateForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class VoiceMnpOutCompletionAndNewOrderCancelRequest {

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
    private VoiceEngagementCancelOrderDateForm voiceEngagementCancelOrderDateForm;

}
