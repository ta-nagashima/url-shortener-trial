package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.*;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpInActivationDueDateForm implements FormToValueObject<MnpInActivationDueDate> {

    @Getter
    @DateFormat
    private String value;

    @Override
    public MnpInActivationDueDate getValueObject(){
        if(value == "") {
            return new NotExistMnpInActivationDueDate();
        }
        return new ValidMnpInActivationDueDate(value);
    }
}
