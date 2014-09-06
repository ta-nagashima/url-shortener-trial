package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpalliancephonepostnosimapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderNoMnpAlliancePhoneCounterWithSimApplyService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VoiceNewOrderNoMnpAlliancePhonePostNoSimApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderNoMnpAlliancePhoneCounterWithSimApplyService voiceNewOrderNoMnpAlliancePhoneCounterWithSimApplyService;

    @Autowired
    private VoiceNewOrderNoMnpAlliancePhonePostNoSimApplyResponse voiceNewOrderNoMnpAlliancePhonePostNoSimApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpalliancephonepostnosimapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderNoMnpAlliancePhonePostNoSimApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);
        // MNPなしイオン店頭SIMあり新規申込と処理が同じなのでServiceは流用
        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderNoMnpAlliancePhoneCounterWithSimApplyService.newOrder(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK()
        );

        return voiceNewOrderNoMnpAlliancePhonePostNoSimApplyResponse.build(identificationReceiptNumber);
    }
}
