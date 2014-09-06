package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChargeAmount implements ValueObjectConvertForApi{

    private final String value;

    @Override
    public String getApiValue() {
        return value;
    }
}
