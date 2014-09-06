package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetaillistrefer

import jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetailrefer.VoiceEngagementDetailReferResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainer
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceEngagementDetailListReferResponse {

    @Autowired
    private JsonTemplate template;

    @Autowired
    private VoiceEngagementDetailReferResponse engagementDetailReferResponse;

    public Map build(VoiceEngagementDetailContainerList voiceEngagementDetailContainerList) {

        List<VoiceEngagementDetailContainer> list = voiceEngagementDetailContainerList.getList();

        final String resultCount = "resultCount";

        if(voiceEngagementDetailContainerList.getCount() == 0){
            return template.build( [  (resultCount) : voiceEngagementDetailContainerList.getCount() ] )
        }

        return template.build(
                [
                    (resultCount) : voiceEngagementDetailContainerList.getCount(),
                    "body" : list.collect { voiceEngagementDetailContainer ->
                        ( engagementDetailReferResponse.buildVoiceEngagement(voiceEngagementDetailContainer.getValidVoiceEngagement())
                        + engagementDetailReferResponse.buildMnpIn(voiceEngagementDetailContainer.getMnpIn())
                        + engagementDetailReferResponse.buildMnpOut(voiceEngagementDetailContainer.getMnpOut())
                        + engagementDetailReferResponse.buildEngagementMonthDisengagementCharge(voiceEngagementDetailContainer.getEngagementMonthDisengagementCharge())
                        )

                    }

                ],
        )
    }

}
