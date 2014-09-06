package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGServicePlanSwitchingDate {

    @Getter
    private final String value;

    boolean isFuture(){
        String nowDate = new DateTime().toString("yyyyMMdd");

        if(value.compareTo(nowDate) > 0) {
            return true;
        }

        return false;
    }

    public LteThreeGServicePlanSwitchingDateTime getScenarioValue(){
        String yyyyMMdd = "000000";
        return new LteThreeGServicePlanSwitchingDateTime(value + yyyyMMdd);
    }

    boolean isNotSet(){
        return value.equals("");
    }
}
