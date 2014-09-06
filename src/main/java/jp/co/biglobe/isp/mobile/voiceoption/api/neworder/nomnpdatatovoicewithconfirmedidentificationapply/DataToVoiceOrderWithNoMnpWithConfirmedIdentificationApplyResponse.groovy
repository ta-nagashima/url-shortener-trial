package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpdatatovoicewithconfirmedidentificationapply

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse {

    @Autowired
    private UpdateApiResponse updateApiResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber) {

        Map response = [
                "identificationReceiptNumber": identificationReceiptNumber.getValue()
        ]
        updateApiResponse.build(response);
    }
}
