package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpalliancephonecounternosimapply;

import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.ValidMnpPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * MNPありイオン店頭SIMなし新規申込
 */

@ToString(includeFieldNames = false)
public class VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyRequest {

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

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceMsisdnForm voiceMsisdnForm;


    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpReservationNumberForm mnpReservationNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpFullNameForm mnpFullNameForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpFullNameKanaForm mnpFullNameKanaForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpGenderForm mnpGenderForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpBirthdayForm mnpBirthdayForm;

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

    public MnpInInitialRequestData getMnpInInitialRequestData(){
        return new MnpInInitialRequestData(
                voiceMsisdnForm.getValueObject(),
                mnpReservationNumberForm.getValueObject(),
                getMnpInputItems()
        );
    }

    public ValidMnpPersonalInfo getMnpInputItems() {
        return new ValidMnpPersonalInfo(this.mnpFullNameForm.getValueObject(), this.mnpFullNameKanaForm.getValueObject(),
                this.mnpGenderForm.getValueObject(), this.mnpBirthdayForm.getValueObject());

    }

}
