package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbyneworderresend

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * SIMの変更完了
 */
@Component
class VoiceSimChangeReflectByNewOrderResendResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(){
        updateApiResponse.build();
    }
}
