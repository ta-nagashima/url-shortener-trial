package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpGender;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpGenderForm implements FormToValueObject<MnpGender> {

    @Getter
    @NotBlank
    @EnumForApiValue(MnpGender.class)
    private String value;

    @Override
    public MnpGender getValueObject(){return EnumApiValueConverter.convert(MnpGender.class, value);}

}
