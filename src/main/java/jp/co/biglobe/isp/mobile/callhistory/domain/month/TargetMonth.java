package jp.co.biglobe.isp.mobile.callhistory.domain.month;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * yyyyMM
 */

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class TargetMonth {

    @Getter
    private final String value;

    public Date getFirstDate(){

        DateTime dt = convertToDateTime();

        return dt.dayOfMonth().roundCeilingCopy().toDate();
    }

    public Date getLastDate(){

        DateTime dt = convertToDateTime();

        return dt.dayOfMonth().withMaximumValue().hourOfDay().withMaximumValue().millisOfDay().withMaximumValue().toDate();
    }


    private DateTime convertToDateTime(){
        return DateTimeFormat.forPattern("yyyyMM").parseDateTime(value);
    }



}
