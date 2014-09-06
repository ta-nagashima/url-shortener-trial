package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class EquipmentNumber implements ValueObjectConvertForApi{

    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }
}
