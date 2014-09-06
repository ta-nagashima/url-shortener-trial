package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpdatatovoicewithconfirmedidentificationapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.DataToVoiceOrderWithNoMnpWithConfirmedIdentificationService;
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
public class DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private DataToVoiceOrderWithNoMnpWithConfirmedIdentificationService dataToVoiceOrderWithNoMnpWithConfirmedIdentificationService;

    @Autowired
    private DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse dataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse;


    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpdatatovoicewithconfirmedidentificationapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid DataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = dataToVoiceOrderWithNoMnpWithConfirmedIdentificationService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK()
        );
        return dataToVoiceOrderWithNoMnpWithConfirmedIdentificationApplyResponse.build(identificationReceiptNumber);
    }


}
