package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpapply;

import jp.co.biglobe.isp.auth.api.form.UserIdForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationInitialRequestData;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementInitialRequestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
public class VoiceNewOrderNoMnpApplyRequest {

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



}
