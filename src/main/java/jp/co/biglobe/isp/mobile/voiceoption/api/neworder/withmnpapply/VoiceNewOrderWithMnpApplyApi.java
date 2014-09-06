package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderWithMnpApplyService;
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
 * MNPあり新規申込
 */
@Controller
public class VoiceNewOrderWithMnpApplyApi {

    @Autowired
    private MnpPersonalInfoValidator mnpPersonalInfoValidator;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderWithMnpApplyService voiceNewOrderWithMnpApplyService;

    @Autowired
    private VoiceNewOrderWithMnpApplyResponse voiceNewOrderWithMnpApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderWithMnpApplyRequest request, Errors errors) {

        mnpPersonalInfoValidator.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderWithMnpApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestData(),
                request.getMnpInInitialRequestData()
        );

        return voiceNewOrderWithMnpApplyResponse.build(identificationReceiptNumber);

    }

}
