package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementService;
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
public class VoiceEngagementApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceEngagementService voiceEngagementService;

    @Autowired
    private VoiceEngagementApplyResponse voiceEngagementApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/engagementapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceEngagementApplyRequest voiceEngagementApplyRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);
        voiceEngagementService.engage(
                voiceEngagementApplyRequest.getEquipmentNumberForm().getValueObject(),
                voiceEngagementApplyRequest.getVoiceEngagementStartDateForm().getValueObject()
        );

        return voiceEngagementApplyResponse.build();
    }
}
