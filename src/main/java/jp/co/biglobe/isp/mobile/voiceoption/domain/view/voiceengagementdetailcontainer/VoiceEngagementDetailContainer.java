package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer;


import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.NotExistEngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.ValidEngagementMonthDisengagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.ValidEngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.NotExistMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.NotExistMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import lombok.Getter;


public class VoiceEngagementDetailContainer {

    @Getter
    private final ValidVoiceEngagement validVoiceEngagement;

    @Getter
    private final MnpIn mnpIn;

    @Getter
    private final MnpOut mnpOut;

    @Getter
    private final EngagementMonthDisengagementCharge engagementMonthDisengagementCharge;

    public VoiceEngagementDetailContainer(
            ValidVoiceEngagement validVoiceEngagement,
            ValidMnpIn validMnpIn,
            ValidMnpOut validMnpOut,
            ValidEngagementMonthDisengagementCharge validEngagementMonthDisengagementCharge){

        this.validVoiceEngagement = validVoiceEngagement;
        this.mnpIn = verifyMnpIn(validMnpIn);
        this.mnpOut = verifyMnpOut(validMnpOut);
        this.engagementMonthDisengagementCharge = verifyEngagementMonthDisengagementCharge(validEngagementMonthDisengagementCharge);

    }

    private MnpIn verifyMnpIn(ValidMnpIn validMnpIn) {
        return validMnpIn == null ? new NotExistMnpIn() : validMnpIn;
    }

    private MnpOut verifyMnpOut(ValidMnpOut validMnpOut) {
        return validMnpOut == null ? new NotExistMnpOut() : validMnpOut;
    }

    private EngagementMonthDisengagementCharge verifyEngagementMonthDisengagementCharge(ValidEngagementMonthDisengagementCharge validEngagementMonthDisengagementCharge) {
        return validEngagementMonthDisengagementCharge == null ? new NotExistEngagementMonthDisengagementCharge() : validEngagementMonthDisengagementCharge;
    }
}
