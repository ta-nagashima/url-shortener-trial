package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.rollbackapply

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 新規契約ロールバック
 */

@Component
class VoiceNewOrderRollbackResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build() {

        updateApiResponse.build();
    }

}
