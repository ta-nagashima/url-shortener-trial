package jp.co.biglobe.isp.mobile.voiceoption.testdata.api.mnpout.mnpoutandcancel

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MnpOutAndCancelCreateResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(String response){
        updateApiResponse.build(
                [
                        response: response
                ]
        );
    }
}
