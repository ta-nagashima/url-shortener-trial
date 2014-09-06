package jp.co.biglobe.isp.mobile.biglobemember.domain.course;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class CourseSwitchingDate {

    private final String value;

    boolean isFuture(){
        String nowDate = new DateTime().toString("yyyyMMdd");

        if(value.compareTo(nowDate) > 0) {
            return true;
        }

        return false;
    }
}
