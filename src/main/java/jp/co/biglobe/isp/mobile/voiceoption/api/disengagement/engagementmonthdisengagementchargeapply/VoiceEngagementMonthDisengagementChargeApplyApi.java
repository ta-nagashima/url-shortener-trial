package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.engagementmonthdisengagementchargeapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceEngagementMonthDisengagementChargeApplyService;
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
 * 契約月解約課金
 */

@Controller
public class VoiceEngagementMonthDisengagementChargeApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceEngagementMonthDisengagementChargeApplyService voiceEngagementMonthDisengagementChargeApplyService;

    @Autowired
    private VoiceEngagementMonthDisengagementChargeApplyResponse voiceEngagementMonthDisengagementChargeApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/engagementmonthdisengagementchargeapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceEngagementMonthDisengagementChargeApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceEngagementMonthDisengagementChargeApplyService.charge(request.getEquipmentNumberForm().getValueObject());

        return voiceEngagementMonthDisengagementChargeApplyResponse.build();
    }
}
