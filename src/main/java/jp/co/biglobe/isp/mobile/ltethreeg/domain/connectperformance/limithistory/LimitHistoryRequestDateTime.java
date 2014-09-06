package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory;

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
public class LimitHistoryRequestDateTime implements ValueObjectConvertForApi {

    private final Date value;

    public static LimitHistoryRequestDateTime create(){
        return new LimitHistoryRequestDateTime(new DateTime().toDate());
    }

    @Override
    public String getApiValue(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
