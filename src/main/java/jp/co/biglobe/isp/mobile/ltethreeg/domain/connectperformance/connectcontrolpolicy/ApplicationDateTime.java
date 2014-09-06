package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;

@AllArgsConstructor
public class ApplicationDateTime implements ValueObjectConvertForApi {

    private Date value;

    @Override
    public String getApiValue(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }

    public static ApplicationDateTime create(){
        return new ApplicationDateTime(new DateTime().toDate());
    }
}
