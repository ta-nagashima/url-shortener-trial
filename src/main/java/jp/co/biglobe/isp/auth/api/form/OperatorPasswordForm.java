package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class OperatorPasswordForm implements FormToValueObject<OperatorPassword> {

    @Getter
    private String value;

    public boolean isValueBlank() {
        return value.length() == 0;
    }

    @Override
    public OperatorPassword getValueObject() {
        return new OperatorPassword(value);
    }
}
