package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementapply

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceEngagementApplyResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(){
        updateApiResponse.build();
    }

}
