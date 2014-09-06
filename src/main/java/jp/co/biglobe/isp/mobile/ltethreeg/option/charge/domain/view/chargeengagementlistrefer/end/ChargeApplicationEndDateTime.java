package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChargeApplicationEndDateTime implements ValueObjectConvertForApi {
    private final Date value;

    @Override
    public String getApiValue() {
        return DateToString.get_yyyyMMddHHmmss(value);
    }

}
