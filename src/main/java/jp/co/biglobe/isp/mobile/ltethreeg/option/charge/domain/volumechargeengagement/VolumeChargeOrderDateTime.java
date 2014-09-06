package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

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
public class VolumeChargeOrderDateTime implements ValueObjectConvertForApi {
    private final Date value;

    public static VolumeChargeOrderDateTime create(){
        return new VolumeChargeOrderDateTime(new DateTime().toDate());
    }

    public String getValueString(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }

    @Override
    public String getApiValue() {
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
