package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpreservationnumberwaiting;

import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class MnpReservationNumberWaitingCreateRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

}
