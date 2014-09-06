package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * 単価の情報（5/通とか）
 *
 *
 */


@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class UnitPrice implements ValueObjectConvertForApi {

    @Getter
    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }
}
