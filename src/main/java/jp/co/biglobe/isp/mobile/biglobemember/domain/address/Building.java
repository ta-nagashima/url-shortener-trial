package jp.co.biglobe.isp.mobile.biglobemember.domain.address;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * ビル名
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class Building implements ValueObjectConvertForApi {

    @Getter
    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }
}
