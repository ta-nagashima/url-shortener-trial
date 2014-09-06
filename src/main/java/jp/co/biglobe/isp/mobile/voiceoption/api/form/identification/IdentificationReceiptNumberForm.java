package jp.co.biglobe.isp.mobile.voiceoption.api.form.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
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
public class IdentificationReceiptNumberForm  implements FormToValueObject<IdentificationReceiptNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{6}_[0-9]{4}$")
    private String value;

    @Override
    public IdentificationReceiptNumber getValueObject(){
        return new IdentificationReceiptNumber(value);
    }
}
