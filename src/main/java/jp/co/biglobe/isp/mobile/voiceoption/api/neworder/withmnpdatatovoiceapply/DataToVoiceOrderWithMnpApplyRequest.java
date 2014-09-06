package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoiceapply;

import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ReceiptStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.NotExistMnpPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.ValidMnpPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
public class DataToVoiceOrderWithMnpApplyRequest {

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
    @NotNull
    @Valid
    private ReceiptStatusForm receiptStatusForm;

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
    @Valid
    private MnpFullNameOptionForm mnpFullNameForm;

    @Getter
    @Setter
    @Valid
    private MnpFullNameKanaOptionForm mnpFullNameKanaForm;

    @Getter
    @Setter
    @Valid
    private MnpGenderOptionForm mnpGenderForm;

    @Getter
    @Setter
    @Valid
    private MnpBirthdayOptionForm mnpBirthdayForm;

    public VoiceEngagementInitialRequestData getVoiceEngagementInitialData(){

        return new VoiceEngagementInitialRequestData(
                voiceUserOrderDateForm.getValueObject(),
                voiceJoinCodeForm.getValueObject(),
                equipmentNumberForm.getValueObject(),
                userIdForm.getValueObject(),
                lteThreeGEngagementNumberForm.getValueObject()
        );
    }

    public IdentificationInitialRequestData getIdentificationInitialRequestData(){

        return new IdentificationInitialRequestData(
                userIdForm.getValueObject(),
                lteThreeGEngagementNumberForm.getValueObject(),
                receiptStatusForm.getValueObject()
        );
    }

    public MnpInInitialRequestData getMnpInInitialRequestData(){
        return new MnpInInitialRequestData(
                voiceMsisdnForm.getValueObject(),
                mnpReservationNumberForm.getValueObject(),
                getMnpInputItems()
        );
    }

    public MnpPersonalInfo getMnpInputItems(){

        if(receiptStatusForm.getValueObject().equals(ReceiptStatus.CONSTANCY)){
            return new ValidMnpPersonalInfo(
                    mnpFullNameForm.getValueObject(),
                    mnpFullNameKanaForm.getValueObject(),
                    mnpGenderForm.getValueObject(),
                    mnpBirthdayForm.getValueObject()
            );
        }

        return new NotExistMnpPersonalInfo();
    }

}
