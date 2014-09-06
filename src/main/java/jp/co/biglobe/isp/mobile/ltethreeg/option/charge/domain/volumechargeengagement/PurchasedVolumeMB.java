package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.text.NumberFormat;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class PurchasedVolumeMB implements ValueObjectConvertForApi {
    private final Integer value;

    public String getValueSeparateComma(){
        return NumberFormat.getNumberInstance().format(value);
    }

    @Override
    public String getApiValue() {
        return value.toString();
    }
}
