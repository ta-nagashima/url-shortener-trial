package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbyneworderresend;

import jp.co.biglobe.isp.mobile.voiceoption.service.simchange.VoiceSimChangeReflectService;
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
 * SIMの変更反映_新規申込の再送
 */
@Controller
public class VoiceSimChangeReflectByNewOrderResendApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceSimChangeReflectService voiceSimChangeReflectService;

    @Autowired
    private VoiceSimChangeReflectByNewOrderResendResponse voiceSimChangeReflectByNewOrderResendResponse;



    @RequestMapping(value = "/voiceoption/outside/skip/simchangereflectbyneworderresend/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceSimChangeReflectByNewOrderResendRequest voiceSimChangeReflectByNewOrderResendRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceSimChangeReflectService.update(
                voiceSimChangeReflectByNewOrderResendRequest.getOldEquipmentNumberForm().getValueObject(),
                voiceSimChangeReflectByNewOrderResendRequest.getNewEquipmentNumberForm().getValueObject()
        );

        return voiceSimChangeReflectByNewOrderResendResponse.build();

    }

}
