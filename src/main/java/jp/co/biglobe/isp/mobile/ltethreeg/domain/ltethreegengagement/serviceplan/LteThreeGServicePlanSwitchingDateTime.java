package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGServicePlanSwitchingDateTime {

    @Getter
    private final String value;

}
