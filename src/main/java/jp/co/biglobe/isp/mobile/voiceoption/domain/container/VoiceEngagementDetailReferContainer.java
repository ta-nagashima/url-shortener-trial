package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementDetailReferContainer {

    @Getter
    private final VoiceEngagement voiceEngagement;

    @Getter
    private final Identification identification;

    @Getter
    private final MnpIn mnpIn;

    @Getter
    private final MnpOut mnpOut;

    @Getter
    private final DisengagementCharge disengagementCharge;

    @Getter
    private final EngagementMonthDisengagementCharge engagementMonthDisengagementCharge;

    public boolean isNecessaryToMnpOutIntentionCheck(){
        return this.voiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);
    }

    public boolean isMnpInExist(){
        return this.mnpIn.isExist();
    }


    public String getVoiceEngagementNumberApiValue(){
        if(voiceEngagement.isNotExist()){
            return null;
        }

        return ((ValidVoiceEngagement)voiceEngagement).getVoiceEngagementNumber().getApiValue();
    }

    public String getMnpInMsisdnApiValue(){
        if(mnpIn.isNotExist()){
            return null;
        }

        return ((ValidMnpIn)mnpIn).getVoiceMsisdn().getApiValue();
    }

}
