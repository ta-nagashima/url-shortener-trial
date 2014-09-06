package jp.co.biglobe.isp.mobile.voiceoption.api.neworderprovisionalresume.withmnp

import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceProvisionalResumeWithMnpResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber){

        Map response = [
                "identificationReceiptNumber" : identificationReceiptNumber.getValue()
        ]

        updateApiResponse.build(response);
    }
}
