package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoicewithconfirmedidentificationapply

import groovy.transform.AutoClone
import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpdatatovoicewithconfirmedidentificationapply.DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class DataToVoiceOrderWithMnpWithConfirmedIdentificationApplyResponse {


    @Autowired
    private DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse dataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber) {
        dataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse.build(identificationReceiptNumber);
    }
}
