package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpGender;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpGenderOptionForm implements FormToValueObject<MnpGender> {

    @Getter
    @EnumForApiValue(MnpGender.class)
    private String value;

    @Override
    public MnpGender getValueObject(){return EnumApiValueConverter.convert(MnpGender.class, value);}

}
