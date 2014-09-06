package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
class FirstUploadDateTime implements ValueObjectConvertForApi {

    private final Date value;

    public FirstUploadDateTime(Date date){
        this.value = new Date(date.getTime());
    }

    @Override
    public String getApiValue() {return DateToString.get_yyyyMMddHHmmss(value);}
}
