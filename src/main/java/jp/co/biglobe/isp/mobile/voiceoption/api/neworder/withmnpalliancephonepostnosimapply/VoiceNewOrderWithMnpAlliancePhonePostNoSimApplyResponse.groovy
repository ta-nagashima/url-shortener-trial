package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpalliancephonepostnosimapply

import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpapply.VoiceNewOrderNoMnpApplyResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * MNPありイオン郵送SIMなし新規申込
 */

@Component
class VoiceNewOrderWithMnpAlliancePhonePostNoSimApplyResponse {

    @Autowired
    private VoiceNewOrderNoMnpApplyResponse newOrderNoMnpApplyResponse;

    public Map build(IdentificationReceiptNumber identificationReceiptNumber){
        newOrderNoMnpApplyResponse.build(identificationReceiptNumber);
    }

}
