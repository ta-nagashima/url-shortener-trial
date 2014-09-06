package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoiceapply

import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpdatatovoiceapply.DataToVoiceOrderNoMnpApplyResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DataToVoiceOrderWithMnpApplyResponse {

    @Autowired
    private DataToVoiceOrderNoMnpApplyResponse dataToVoiceOrderNoMnpApplyResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber){
        dataToVoiceOrderNoMnpApplyResponse.build(identificationReceiptNumber);
    }
}
