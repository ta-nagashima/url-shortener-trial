package jp.co.biglobe.isp.auth.domain.user;


import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 氏名（フルネーム）
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class FullName implements ValueObjectConvertForApi {

    @Getter
    private final String value;

    @Override
    public String getApiValue() {
        return value;
    }
}
