package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementcancelapply

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 解約予約キャンセル
 */

@Component
class VoiceDisengagementCancelApplyResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build() {
        updateApiResponse.build();
    }
}
