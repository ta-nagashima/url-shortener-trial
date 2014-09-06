package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpalliancephonepostnosimapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * MNPありイオン郵送SIMなし新規申込
 */

@Controller
public class VoiceNewOrderWithMnpAlliancePhonePostNoSimApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService;

    @Autowired
    private VoiceNewOrderWithMnpAlliancePhonePostNoSimApplyResponse voiceNewOrderWithMnpAlliancePhonePostNoSimApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpalliancephonepostnosimapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderWithMnpAlliancePhonePostNoSimApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // MNPありイオン店頭SIMなし新規申込と処理が同じなのでServiceは流用
        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK(),
                request.getMnpInInitialRequestData()
        );

        return voiceNewOrderWithMnpAlliancePhonePostNoSimApplyResponse.build(identificationReceiptNumber);
    }
}
