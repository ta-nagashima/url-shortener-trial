package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidDisengagementCharge implements DisengagementCharge {

    private final VoiceEngagementNumber voiceEngagementNumber;

    private final VoiceDisengagementChargeEndMonth voiceDisengagementChargeEndMonth;

    private final VoiceDisengagementChargeAmount voiceDisengagementChargeAmount;

    boolean isCharge(){
        return (this.voiceDisengagementChargeEndMonth.isAfterNowMonth() ||
                    this.voiceDisengagementChargeEndMonth.isEqualNowMonth());
    }

    boolean isNotCharge() {
        return !isCharge();
    }

    @Override
    public String getApiValueForCostingStatus() {
        return isCharge() ? VoiceDisengagementChargeCostingStatus.COST.getNoRefactoringValue():
                VoiceDisengagementChargeCostingStatus.NOT_COST.getNoRefactoringValue();
    }

    @Override
    public String getApiValueForCostingEndMonth() {
        return voiceDisengagementChargeEndMonth.getValue();
    }

    @Override
    public String getApiValueForCostingAmount() {
        return voiceDisengagementChargeAmount.getStringValue();
    }


}
