package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpBirthday;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpBirthdayForm implements FormToValueObject<MnpBirthday> {

    @Getter
    @NotBlank
    @DateFormat
    private String value;

    @Override
    public MnpBirthday getValueObject(){
        return new MnpBirthday(value);
    }
}
