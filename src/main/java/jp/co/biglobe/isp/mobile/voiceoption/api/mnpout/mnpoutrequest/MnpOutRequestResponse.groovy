package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrequest

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutRequestInfoReturnContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 転出依頼
 */
@Component
class MnpOutRequestResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(MnpOutRequestInfoReturnContainer mnpOutRequestInfoReturnContainer) {

        ValidVoiceEngagement voiceEngagement = mnpOutRequestInfoReturnContainer.getVoiceEngagement()
        ValidMnpOut mnpOut = mnpOutRequestInfoReturnContainer.getValidMnpOut()

        template.build(
                buildVoiceEngagement(voiceEngagement) + buildMnpOut(mnpOut)
        );

    }

    public Map buildVoiceEngagement(ValidVoiceEngagement validVoiceEngagement) {

        return [
                "voiceEngagement": [
                        "voiceEngagementNumber": validVoiceEngagement.getVoiceEngagementNumber().getApiValue(),
                        "userId"               : validVoiceEngagement.getVoiceEngagementLinkage().getUserId().getApiValue(),
                ]
        ]
    }

    public Map buildMnpOut(ValidMnpOut validMnpOut) {

        return [
                "mnpOut": [
                        "mnpMsisdn"      : validMnpOut.getMnpOutMsisdn().getApiValue(),
                        "mnpFullName"    : validMnpOut.getValidMnpOutPersonalInfo().getMnpFullNameForApiValue(),
                        "mnpFullNameKana": validMnpOut.getValidMnpOutPersonalInfo().getMnpFullNameKanaForApiValue(),
                        "mnpGender"      : validMnpOut.getValidMnpOutPersonalInfo().getMnpGenderForApiValue(),
                        "mnpBirthday"    : validMnpOut.getValidMnpOutPersonalInfo().getMnpBirthdayForApiValue(),
                ]
        ]
    }

}
