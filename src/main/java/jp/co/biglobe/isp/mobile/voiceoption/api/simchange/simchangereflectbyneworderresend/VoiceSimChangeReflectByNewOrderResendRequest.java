package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbyneworderresend;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 *  * SIMの変更完了
 */
@ToString(includeFieldNames = false)
public class VoiceSimChangeReflectByNewOrderResendRequest {

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
