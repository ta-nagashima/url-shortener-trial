package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutcancel

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
public class MnpOutCancelResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(){
        updateApiResponse.build();
    }

}
