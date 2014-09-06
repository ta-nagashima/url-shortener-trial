package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoiceapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.DataToVoiceOrderWithMnpApplyService;
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
public class DataToVoiceOrderWithMnpApplyApi {

    @Autowired
    private MnpPersonalInfoForDataToVoiceValidator mnpPersonalInfoForDataToVoiceValidator;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private DataToVoiceOrderWithMnpApplyService dataToVoiceOrderWithMnpApplyService;

    @Autowired
    private DataToVoiceOrderWithMnpApplyResponse voiceNewOrderWithMnpApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpadatatovoicepply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid DataToVoiceOrderWithMnpApplyRequest request, Errors errors) {

        mnpPersonalInfoForDataToVoiceValidator.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = dataToVoiceOrderWithMnpApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestData(),
                request.getMnpInInitialRequestData()
        );
        return voiceNewOrderWithMnpApplyResponse.build(identificationReceiptNumber);
    }
}
