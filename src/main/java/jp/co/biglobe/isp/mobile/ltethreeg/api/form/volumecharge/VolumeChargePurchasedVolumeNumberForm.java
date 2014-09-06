package jp.co.biglobe.isp.mobile.ltethreeg.api.form.volumecharge;

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchasedVolumeNumber;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VolumeChargePurchasedVolumeNumberForm implements FormToValueObject<VolumeChargePurchasedVolumeNumber> {


    @Getter
    @NotBlank
    @Pattern(regexp = "[0-9]*")
    @Max(10)
    @Min(1)
    private String value;

    @Override
    public VolumeChargePurchasedVolumeNumber getValueObject(){
        try {
            return new VolumeChargePurchasedVolumeNumber(Integer.valueOf(value));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
