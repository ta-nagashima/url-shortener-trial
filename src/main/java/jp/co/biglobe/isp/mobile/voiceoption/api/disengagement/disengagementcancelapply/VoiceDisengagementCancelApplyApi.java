package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementcancelapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceDisengagementCancelService;
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
 * 解約予約キャンセル
 */

@Controller
public class VoiceDisengagementCancelApplyApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceDisengagementCancelService voiceDisengagementCancelService;

    @Autowired
    private VoiceDisengagementCancelApplyResponse disengagementCancelResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/disengagementreservationcancel/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceDisengagementCancelApplyRequest disengagementCancelRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceDisengagementCancelService.cancel(
                disengagementCancelRequest.getEquipmentNumberForm().getValueObject()
        );

        return disengagementCancelResponse.build();
    }

}
