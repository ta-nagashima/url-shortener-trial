package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpalliancephonewebnosimapply;

import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceJoinCodeForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.VoiceUserOrderDateForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * イオンWebSimなしMnpなし新規申込
 */
@ToString(includeFieldNames = false)
public class VoiceNewOrderNoMnpAlliancePhoneWebNoSimApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceUserOrderDateForm voiceUserOrderDateForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceJoinCodeForm voiceJoinCodeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private UserIdForm userIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

    public VoiceEngagementInitialRequestData getVoiceEngagementInitialData(){

        return new VoiceEngagementInitialRequestData(
                voiceUserOrderDateForm.getValueObject(),
                voiceJoinCodeForm.getValueObject(),
                equipmentNumberForm.getValueObject(),
                userIdForm.getValueObject(),
                lteThreeGEngagementNumberForm.getValueObject()
        );
    }

    public IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary getIdentificationInitialRequestDataForOK(){

        return new IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary(
                userIdForm.getValueObject(),
                lteThreeGEngagementNumberForm.getValueObject()
        );
    }


}
