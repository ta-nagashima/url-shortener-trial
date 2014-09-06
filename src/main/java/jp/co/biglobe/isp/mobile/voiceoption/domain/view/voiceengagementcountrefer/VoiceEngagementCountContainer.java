package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class VoiceEngagementCountContainer {

    @Getter
    private final VoiceEngagementCount voiceEngagementCountOrdered;

    @Getter
    private final VoiceEngagementCount voiceEngagementCountEngaged;

    @Getter
    private final VoiceEngagementCount voiceEngagementCountDisengagedReserved;

    public boolean isNotExist(){
        if(voiceEngagementCountOrdered.isNotExist() && voiceEngagementCountEngaged.isNotExist()
                && voiceEngagementCountDisengagedReserved.isNotExist()){
            return true;
        }

        return false;
    }

}
