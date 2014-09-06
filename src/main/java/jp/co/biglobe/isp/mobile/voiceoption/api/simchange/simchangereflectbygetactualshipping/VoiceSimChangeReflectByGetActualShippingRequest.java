package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbygetactualshipping;


import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class VoiceSimChangeReflectByGetActualShippingRequest {
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm oldEquipmentNumberForm;


    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm newEquipmentNumberForm;

}
