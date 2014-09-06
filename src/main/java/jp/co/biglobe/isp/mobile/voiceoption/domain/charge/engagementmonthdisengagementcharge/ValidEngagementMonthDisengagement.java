package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidEngagementMonthDisengagement {

    private final VoiceEngagementStatus voiceEngagementStatus;
    private final VoiceEngagementNumber voiceEngagementNumber;
    private final VoiceEngagementStartDate voiceEngagementStartDate;
    private final VoiceEngagementEndDateTime voiceEngagementEndDateTime;

    public static ValidEngagementMonthDisengagement create(ValidVoiceEngagement validVoiceEngagement) {
        return new ValidEngagementMonthDisengagement(
                validVoiceEngagement.getVoiceEngagementStatus(),
                validVoiceEngagement.getVoiceEngagementNumber(),
                validVoiceEngagement.getVoiceEngagementStartDate(),
                validVoiceEngagement.getVoiceEngagementEndDateTime()
        );
    }

    public void check(EngagementMonthDisengagementCharge engagementMonthDisengagementCharge) {
        if (canNotCharge()) {
            throw new BusinessException("契約月解約課金できません", EngagementMonthDisengagementChargeErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
        }

        if (engagementMonthDisengagementCharge.isExist()) {
            throw new BusinessException("すでに課金済みです", EngagementMonthDisengagementChargeErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
        }
    }

    public boolean canCharge(){
        return isDisengaged() && isEngagementMonthDisengagement();
    }

    public boolean canNotCharge() {
        return !canCharge();
    }

    private boolean isDisengaged() {
        return voiceEngagementStatus == VoiceEngagementStatus.DISENGAGED;
    }

    private boolean isEngagementMonthDisengagement() {
        EngagementMonthDisengagementChargeMonth engagementMonthDisengagementChargeMonth =
                new EngagementMonthDisengagementChargeMonth(DateToString.get_yyyyMM(voiceEngagementStartDate.getDate()));
        return engagementMonthDisengagementChargeMonth.isEqualMonth(voiceEngagementEndDateTime);
    }

}
