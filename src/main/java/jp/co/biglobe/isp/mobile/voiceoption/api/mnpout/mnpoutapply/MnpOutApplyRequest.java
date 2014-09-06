package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpOutMailSendStatusForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpOutMsisdnForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class MnpOutApplyRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpOutMsisdnForm mnpOutMsisdnForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpOutMailSendStatusForm mnpOutMailSendStatusForm;


    public boolean isSend(){
        return mnpOutMailSendStatusForm.getValueObject().isSend();
    }

}
