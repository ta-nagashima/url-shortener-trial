package jp.co.biglobe.isp.mobile.voiceoption.api.simchange.simchangereflectbygetactualshipping;

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
 * SIMの変更反映_発送実績取得（SIM変更、再発行）
 */
@Controller
public class VoiceSimChangeReflectByGetActualShippingAPI {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceSimChangeReflectService voiceSimChangeReflectService;

    @Autowired
    private VoiceSimChangeReflectByGetActualShippingResponse voiceSimChangeReflectByGetActualShippingResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/simchangereflectbygetactualshipping/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceSimChangeReflectByGetActualShippingRequest voiceSimChangeReflectByGetActualShippingRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceSimChangeReflectService.update(
                voiceSimChangeReflectByGetActualShippingRequest.getOldEquipmentNumberForm().getValueObject(),
                voiceSimChangeReflectByGetActualShippingRequest.getNewEquipmentNumberForm().getValueObject()
        );

        return voiceSimChangeReflectByGetActualShippingResponse.build();

    }


}
