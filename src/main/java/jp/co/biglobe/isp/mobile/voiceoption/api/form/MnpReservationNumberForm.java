package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
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
public class MnpReservationNumberForm  implements FormToValueObject<MnpReservationNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}-[0-9]{5}$")
    private String value;

    @Override
    public MnpReservationNumber getValueObject(){
        return new MnpReservationNumber(value);
    }
}
