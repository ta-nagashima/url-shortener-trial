package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.status;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class LteThreeGEngagementEndDateTime{

    private final Date value;

    public Date getValue(){
        return new Date(value.getTime());
    }

    public LteThreeGEngagementEndDateTime(Date date){
        this.value = roundMilliSecond(date).toDate();
    }

    boolean isBefore(){
        return endDateTime().isBefore(roundMilliSecond(new Date()));
    }

    boolean isEqual(){
        return endDateTime().isEqual(roundMilliSecond(new Date()));
    }

    boolean isAfter(){
        return endDateTime().isAfter(roundMilliSecond(new Date()));
    }


    private DateTime endDateTime(){
        return new DateTime(value);
    }

    private DateTime roundMilliSecond(Date date) {
        return new DateTime(date).withMillisOfSecond(0);
    }
}
