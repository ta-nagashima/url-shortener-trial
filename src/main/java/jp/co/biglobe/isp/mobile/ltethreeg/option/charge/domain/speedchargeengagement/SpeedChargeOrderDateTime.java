package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;


import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SpeedChargeOrderDateTime implements ValueObjectConvertForApi {
    private final Date value;

    public static SpeedChargeOrderDateTime create(){
        return new SpeedChargeOrderDateTime(new DateTime().toDate());
    }

    public String getValueString(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }

    @Override
    public String getApiValue() {
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
