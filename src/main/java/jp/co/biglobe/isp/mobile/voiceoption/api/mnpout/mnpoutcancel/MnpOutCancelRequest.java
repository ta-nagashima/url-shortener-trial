package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutcancel;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.MnpOutCancelReasonForm;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class MnpOutCancelRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpOutCancelReasonForm mnpOutCancelReasonForm;

}
