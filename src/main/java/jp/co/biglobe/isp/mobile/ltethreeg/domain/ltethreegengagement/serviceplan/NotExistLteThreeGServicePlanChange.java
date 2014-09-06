package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistLteThreeGServicePlanChange implements LteThreeGServicePlanChange{

    @Override
    public boolean isChangeReservation(){
        return false;
    }

    @Override
    public String getBeforeServicePlanCodeScenarioValue(){
        return "";
    }

    @Override
    public LteThreeGServicePlanSwitchingDateTime getSwitchingDateScenarioValue(){
        return new LteThreeGServicePlanSwitchingDateTime("");
    }
}
