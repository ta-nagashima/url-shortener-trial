package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ExpireDateForm implements FormToValueObject<ExpireDate> {

    @Getter
    @NotBlank
    @DateFormat
    private String value;

    @Override
    public ExpireDate getValueObject(){
        return new ExpireDate(value);
    }
}
