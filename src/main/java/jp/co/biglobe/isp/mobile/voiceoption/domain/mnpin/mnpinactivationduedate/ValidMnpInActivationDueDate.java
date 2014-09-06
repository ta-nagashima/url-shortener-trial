package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpInActivationDueDate implements MnpInActivationDueDate, ValueObjectConvertForApi {
    @Getter
    private final String value;

    @Override
    public String getApiValue() {
        return value;
    }

    @Override
    public boolean isExist() {
        return true;
    }
}
