package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public class ValidPenaltyChargeBuilder {

    private final static String BEFORE_MONTH = "201404";

    private final static String FOREVER_MONTH = "299912";


    private VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);

    private VoiceDisengagementChargeEndMonth voiceDisengagementChargeEndMonth = new VoiceDisengagementChargeEndMonth(BEFORE_MONTH);

    private VoiceDisengagementChargeAmount voiceDisengagementChargeAmount = new VoiceDisengagementChargeAmount(6000);


    public ValidPenaltyChargeBuilder costedCharge(){
        this.voiceDisengagementChargeEndMonth = new VoiceDisengagementChargeEndMonth(FOREVER_MONTH);
        return this;
    }


    public ValidDisengagementCharge build(){
        return new ValidDisengagementCharge(
                voiceEngagementNumber,
                voiceDisengagementChargeEndMonth,
                voiceDisengagementChargeAmount
        );
    }

}
