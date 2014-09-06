package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpdatatovoiceapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.DataToVoiceOrderService;
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
public class DataToVoiceOrderNoMnpApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private DataToVoiceOrderService dataToVoiceOrderService;

    @Autowired
    private DataToVoiceOrderNoMnpApplyResponse dataToVoiceOrderNoMnpApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpdatatovoiceapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid DataToVoiceOrderNoMnpApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = dataToVoiceOrderService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestData());

        return dataToVoiceOrderNoMnpApplyResponse.build(identificationReceiptNumber);
    }
}
