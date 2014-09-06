package jp.co.biglobe.isp.mobile.callhistory.domain.summary.single;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@ToString(includeFieldNames = false)
@AllArgsConstructor
@EqualsAndHashCode
public class SumChargeAmount implements ValueObjectConvertForApi {

    @Getter
    private final Integer value;

    @Override
    public String getApiValue() {
        return String.valueOf(value);
    }
}
