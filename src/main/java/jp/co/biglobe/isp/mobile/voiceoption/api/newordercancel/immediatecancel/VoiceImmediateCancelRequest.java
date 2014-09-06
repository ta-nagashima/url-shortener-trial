package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.immediatecancel;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementCancelOrderDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceEngagementCancelReasonForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 即時キャンセル
 */

@ToString(includeFieldNames = false)
public class VoiceImmediateCancelRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementCancelReasonForm voiceEngagementCancelReasonForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementCancelOrderDateForm voiceEngagementCancelOrderDateForm;

}
