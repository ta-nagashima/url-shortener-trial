package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbygetactualshipping

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceSimChangeReflectByGetActualShippingResponse {
    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(){
        updateApiResponse.build();
    }

}
