package jp.co.biglobe.isp.mobile.biglobemember.domain.address;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 都道府県コード
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class PrefectureCode implements ValueObjectConvertForApi {

    @Getter
    private final Integer value;

    @Override
    public String getApiValue(){
        return String.valueOf(value);
    }
}
