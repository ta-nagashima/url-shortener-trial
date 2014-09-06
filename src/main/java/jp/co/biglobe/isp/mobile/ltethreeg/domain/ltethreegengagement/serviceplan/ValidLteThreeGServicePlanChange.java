package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidLteThreeGServicePlanChange implements LteThreeGServicePlanChange{

    @Getter
    private final LteThreeGServicePlanCode beforeLteThreeGServicePlanCode;

    private final LteThreeGServicePlanSwitchingDate lteThreeGServicePlanSwitchingDate;

    @Override
    public String getBeforeServicePlanCodeScenarioValue(){
        return beforeLteThreeGServicePlanCode.getScenarioValue();
    }

    @Override
    public LteThreeGServicePlanSwitchingDateTime getSwitchingDateScenarioValue(){
        return lteThreeGServicePlanSwitchingDate.getScenarioValue();
    }

    @Override
    public boolean isChangeReservation(){

        if(lteThreeGServicePlanSwitchingDate.isFuture()){
            return true;
        }

        return false;

    }
}
