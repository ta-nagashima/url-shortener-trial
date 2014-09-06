package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceDisengagementApplyService;
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
 * 解約
 */

@Controller
public class VoiceDisengagementApplyApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceDisengagementApplyService voiceDisengagementApplyService;

    @Autowired
    private VoiceDisengagementApplyResponse disengagementReserveResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/disengagementapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceDisengagementApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceDisengagementApplyService.disengage(
                request.getEquipmentNumberForm().getValueObject(),
                request.getVoiceEngagementEndDateTimeForm().getValueObject(),
                request.getVoiceEngagementDisengageReasonForm().getValueObject(),
                request.getVoiceEngagementDisengageOrderDateForm().getValueObject()
        );

        return disengagementReserveResponse.build();
    }

}
