package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class OptionalOperatorIdForm implements FormToValueObject<OperatorId> {

    @Getter
    private String value;

    public boolean isValueBlank() {
        return value.length() == 0;
    }

    @Override
    public OperatorId getValueObject() {
        return new OperatorId(value);
    }
}
