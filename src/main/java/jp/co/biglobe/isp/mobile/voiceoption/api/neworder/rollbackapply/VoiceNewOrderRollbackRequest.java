package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.rollbackapply;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.EquipmentNumberForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * 新規契約ロールバック
 */

@ToString(includeFieldNames = false)
public class VoiceNewOrderRollbackRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private EquipmentNumberForm equipmentNumberForm;

}
