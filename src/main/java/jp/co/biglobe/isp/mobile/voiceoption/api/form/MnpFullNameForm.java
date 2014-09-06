package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EucZenkaku;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullName;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpFullNameForm implements FormToValueObject<MnpFullName> {

    @Getter
    @NotBlank
    @EucZenkaku
    private String value;

    @Override
    public MnpFullName getValueObject(){
        return new MnpFullName(value);
    }
}
