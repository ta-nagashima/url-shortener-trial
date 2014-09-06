package jp.co.biglobe.isp.mobile.callhistory.domain.summary.single;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@AllArgsConstructor
@EqualsAndHashCode
public class SumCountCallAndTrans implements ValueObjectConvertForApi {

    @Getter
    private final String value;

    @Override
    public String getApiValue() {
        return value;
    }
}
