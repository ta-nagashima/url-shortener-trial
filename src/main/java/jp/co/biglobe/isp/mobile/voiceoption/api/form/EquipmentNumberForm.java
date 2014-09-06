package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class EquipmentNumberForm implements FormToValueObject<EquipmentNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$")
    private String value;

    @Override
    public EquipmentNumber getValueObject(){
        return new EquipmentNumber(value);
    }
}
