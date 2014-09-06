package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class SpeedChargeApplicationEndDateTime implements ValueObjectConvertForApi{

    private final Date value;

    public SpeedChargeApplicationEndDateTime(Date value){
        this.value = value;
    }

    public static SpeedChargeApplicationEndDateTime create(){
        return new SpeedChargeApplicationEndDateTime(new DateTime().toDate());
    }

    @Override
    public String getApiValue() {
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
