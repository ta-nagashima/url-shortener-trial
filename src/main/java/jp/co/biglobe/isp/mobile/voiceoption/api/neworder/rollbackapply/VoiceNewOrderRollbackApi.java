package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.rollbackapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderRollbackService;
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
 * 新規契約ロールバック
 */

@Controller
public class VoiceNewOrderRollbackApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderRollbackService voiceNewOrderRollbackService;

    @Autowired
    private VoiceNewOrderRollbackResponse voiceNewOrderRollbackResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderrollbackapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderRollbackRequest voiceNewOrderRollbackRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        voiceNewOrderRollbackService.rollback(voiceNewOrderRollbackRequest.getEquipmentNumberForm().getValueObject());

        return voiceNewOrderRollbackResponse.build();

    }


}
