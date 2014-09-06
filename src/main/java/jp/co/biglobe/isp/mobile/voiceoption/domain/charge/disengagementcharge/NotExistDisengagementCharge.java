package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistDisengagementCharge implements DisengagementCharge {


    boolean isCharge() {
        return false;
    }


    boolean isNotCharge() {
        return !isCharge();
    }

    @Override
    public String getApiValueForCostingStatus() {
        return VoiceDisengagementChargeCostingStatus.NOT_COST.getNoRefactoringValue();
    }

    @Override
    public String getApiValueForCostingEndMonth() {
        return "";
    }

    @Override
    public String getApiValueForCostingAmount() {
        return "";
    }


}
