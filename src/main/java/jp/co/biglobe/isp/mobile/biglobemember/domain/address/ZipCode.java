package jp.co.biglobe.isp.mobile.biglobemember.domain.address;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 郵便番号
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ZipCode implements ValueObjectConvertForApi {

    @Getter
    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }
}
