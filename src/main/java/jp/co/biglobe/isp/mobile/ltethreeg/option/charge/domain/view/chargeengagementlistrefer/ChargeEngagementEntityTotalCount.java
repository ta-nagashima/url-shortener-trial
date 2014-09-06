package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChargeEngagementEntityTotalCount implements ValueObjectConvertForApi {

    private final Integer value;

    @Override
    public String getApiValue(){
        return String.valueOf(value);
    }
}

