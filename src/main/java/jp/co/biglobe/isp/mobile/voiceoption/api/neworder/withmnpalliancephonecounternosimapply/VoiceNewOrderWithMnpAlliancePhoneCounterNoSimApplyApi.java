package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpalliancephonecounternosimapply;

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
 * MNPありイオン店頭SIMなし新規申込
 */

@Controller
public class VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService;

    @Autowired
    private VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyResponse voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpalliancephonecounternosimapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK(),
                request.getMnpInInitialRequestData()
        );

        return voiceNewOrderWithMnpAlliancePhoneCounterNoSimApplyResponse.build(identificationReceiptNumber);
    }
}
