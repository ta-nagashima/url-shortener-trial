package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.immediatecancel

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 即時キャンセル
 */

@Component
class VoiceImmediateCancelResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build() {
        updateApiResponse.build();
    }

}
