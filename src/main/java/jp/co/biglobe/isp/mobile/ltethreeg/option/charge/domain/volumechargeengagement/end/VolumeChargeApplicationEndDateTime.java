package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class VolumeChargeApplicationEndDateTime implements ValueObjectConvertForApi {
    private final Date value;

    public VolumeChargeApplicationEndDateTime(Date value){
        this.value = value;
    }

    public static VolumeChargeApplicationEndDateTime create(){
        return new VolumeChargeApplicationEndDateTime(new DateTime().toDate());
    }

    @Override
    public String getApiValue() {
        return DateToString.get_yyyyMMddHHmmss(value);
    }

}
