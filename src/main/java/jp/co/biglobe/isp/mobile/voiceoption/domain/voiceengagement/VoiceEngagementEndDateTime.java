package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class VoiceEngagementEndDateTime implements ValueObjectConvertForApi{

    private final Date value;

    public Date getValue(){
        return new Date(value.getTime());
    }

    @Override
    public String getApiValue(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }

    public VoiceEngagementEndDateTime(Date date){
//        this.value = new Date(date.getTime());
        this.value = roundMilliSecond(date).toDate();
    }

    boolean isBefore(Date now){
        return endDateTime().isBefore(roundMilliSecond(now));
    }

    boolean isEqual(Date now){
        return endDateTime().isEqual(roundMilliSecond(now));
    }

    boolean isAfter(Date now){
        return endDateTime().isAfter(roundMilliSecond(now));
    }

    public int getMonth() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(value);
        return cal.get(Calendar.MONTH) + 1;
    }

    private DateTime endDateTime(){
        return new DateTime(value);
    }

    private DateTime roundMilliSecond(Date date) {
        return new DateTime(date).withMillisOfSecond(0);
    }
}
