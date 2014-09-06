package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpwithconfirmedidentificationapply

import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpapply.VoiceNewOrderNoMnpApplyResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceNewOrderWithMnpWithConfirmedIdentificationApplyResponse {


    @Autowired
    private VoiceNewOrderNoMnpApplyResponse newOrderNoMnpApplyResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber){
        newOrderNoMnpApplyResponse.build(identificationReceiptNumber);
    }
}
