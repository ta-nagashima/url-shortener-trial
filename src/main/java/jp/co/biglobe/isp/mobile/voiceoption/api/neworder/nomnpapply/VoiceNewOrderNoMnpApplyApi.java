package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderService;
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
 * MNPなし新規申込
 */
@Controller
public class VoiceNewOrderNoMnpApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderService voiceNewOrderService;

    @Autowired
    private VoiceNewOrderNoMnpApplyResponse voiceNewOrderNoMnpApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderNoMnpApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestData());

        return voiceNewOrderNoMnpApplyResponse.build(identificationReceiptNumber);

    }

}
