package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementbyltethreegengagementnumbercheck;

import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.DisengagementCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceDisengagementCheckService;
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
public class VoiceDisengagementByLteThreeGEngagementNumberCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceDisengagementCheckService voiceDisengagementCheckService;

    @Autowired
    private VoiceDisengagementByLteThreeGEngagementNumberCheckResponse voiceDisengagementByLteThreeGEngagementNumberCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/disengagementbyltethreegengagementnumber/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceDisengagementByLteThreeGEngagementNumberCheckRequest voiceDisengagementByLteThreeGEngagementNumberCheckRequest,
                      Errors errors) {

        alarmValidationVerifier.verify(errors);

        DisengagementCheckStatusContainer disengagementCheckStatusContainer = voiceDisengagementCheckService.check(
                voiceDisengagementByLteThreeGEngagementNumberCheckRequest.getLteThreeGEngagementNumberForm().getValueObject()
        );

        DisengagementCharge disengagementCharge = voiceDisengagementCheckService.getPenaltyCharge(
                voiceDisengagementByLteThreeGEngagementNumberCheckRequest.getLteThreeGEngagementNumberForm().getValueObject()
        );

        return voiceDisengagementByLteThreeGEngagementNumberCheckResponse.build(disengagementCheckStatusContainer, disengagementCharge);
    }

}
