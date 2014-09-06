package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementcountrefer

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceEngagementCountReferResponse {
    @Autowired
    private JsonTemplate template;

    public Map build(VoiceEngagementCountContainer voiceEngagementCountContainer) {
        final String VOICE_ENGAGEMENT_RESULT = "result";

        if(voiceEngagementCountContainer.isNotExist()){
            return         template.build(
                    [
                            (VOICE_ENGAGEMENT_RESULT) : StereotypedCharacters.NOT_EXIST,
                            orderedCount:voiceEngagementCountContainer.getVoiceEngagementCountOrdered().getApiValue(),
                            engagedCount:voiceEngagementCountContainer.getVoiceEngagementCountEngaged().getApiValue(),
                            disengagedReservationCount:voiceEngagementCountContainer.getVoiceEngagementCountDisengagedReserved().getApiValue()
                    ]
            )
        }


        template.build(
                [
                        (VOICE_ENGAGEMENT_RESULT) : StereotypedCharacters.EXIST,
                        orderedCount:voiceEngagementCountContainer.getVoiceEngagementCountOrdered().getApiValue(),
                        engagedCount:voiceEngagementCountContainer.getVoiceEngagementCountEngaged().getApiValue(),
                        disengagedReservationCount:voiceEngagementCountContainer.getVoiceEngagementCountDisengagedReserved().getApiValue()
                ]
        )
    }
}
