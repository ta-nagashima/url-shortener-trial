package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutconfirmfordisengagerefer

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MnpOutConfirmForDisengageReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(VoiceEngagementDetailReferContainer container) {

        final String CHECK_RESULT = "necessaryToMnpOutIntentionCheck";
        if (!container.isNecessaryToMnpOutIntentionCheck()) {
            return template.build([(CHECK_RESULT): "unnecessary"]);

        }

        return template.build(
                [
                        (CHECK_RESULT): "necessary",
                ]
                        + buildIntentionCheckInfo(container)
        );
    }

    public Map buildIntentionCheckInfo(VoiceEngagementDetailReferContainer container) {

        Map map = [
                "intentionCheckInfo": [
                        "voiceEngagementNumber": container.getVoiceEngagementNumberApiValue()
                ]
        ]
        return map;
    }

}
