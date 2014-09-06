package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementMonthDisengagementChargeDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidEngagementMonthDisengagementCharge implements EngagementMonthDisengagementCharge {

    private final VoiceEngagementMonthDisengagementChargeDateTime voiceEngagementMonthDisengagementChargeDateTime;

    private final EngagementMonthDisengagementFee engagementMonthDisengagementFee;

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() {
        return !isExist();
    }

    @Override
    public String getApiValueForExecuteDateTime() {
        return voiceEngagementMonthDisengagementChargeDateTime.getApiValue();
    }

    @Override
    public String getApiValueForChargeAmount() {
        return engagementMonthDisengagementFee.getApiValue();
    }
}
