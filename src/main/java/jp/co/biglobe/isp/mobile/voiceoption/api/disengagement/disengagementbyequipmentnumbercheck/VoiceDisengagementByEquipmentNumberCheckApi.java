package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementbyequipmentnumbercheck;

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
public class VoiceDisengagementByEquipmentNumberCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceDisengagementCheckService voiceDisengagementCheckService;

    @Autowired
    private VoiceDisengagementByEquipmentNumberCheckResponse voiceDisengagementByEquipmentNumberCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/disengagementbyequipmentnumber/check", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceDisengagementByEquipmentNumberCheckRequest voiceDisengagementByEquipmentNumberCheckRequest,
                      Errors errors) {

        alarmValidationVerifier.verify(errors);

        DisengagementCheckStatusContainer disengagementCheckStatusContainer = voiceDisengagementCheckService.check(
                voiceDisengagementByEquipmentNumberCheckRequest.getEquipmentNumberForm().getValueObject()
        );

        DisengagementCharge disengagementCharge = voiceDisengagementCheckService.getPenaltyCharge(
                voiceDisengagementByEquipmentNumberCheckRequest.getEquipmentNumberForm().getValueObject()
        );

        return voiceDisengagementByEquipmentNumberCheckResponse.build(disengagementCheckStatusContainer, disengagementCharge);
    }

}
