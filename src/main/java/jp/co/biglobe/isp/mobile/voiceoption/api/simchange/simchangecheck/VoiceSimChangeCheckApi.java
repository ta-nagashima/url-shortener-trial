package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangecheck;

import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.SimChangeCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.simchange.VoiceSimChangeCheckService;
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
public class VoiceSimChangeCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceSimChangeCheckService voiceSimChangeCheckService;

    @Autowired
    private VoiceSimChangeCheckResponse voiceSimChangeCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/simchange/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceSimChangeCheckRequest voiceSimChangeCheckRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        SimChangeCheckStatus simChangeCheckStatus = voiceSimChangeCheckService.check(
                voiceSimChangeCheckRequest.getEquipmentNumberForm().getValueObject());

        return voiceSimChangeCheckResponse.build(simChangeCheckStatus);
    }
}
