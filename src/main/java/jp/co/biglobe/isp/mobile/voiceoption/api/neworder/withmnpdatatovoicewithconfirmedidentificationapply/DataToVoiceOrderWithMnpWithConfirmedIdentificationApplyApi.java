package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoicewithconfirmedidentificationapply;

import jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpdatatovoiceapply.MnpPersonalInfoForDataToVoiceValidator;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.DataToVoiceOrderWithMnpWithConfirmedIdentificationService;
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
public class DataToVoiceOrderWithMnpWithConfirmedIdentificationApplyApi {

    @Autowired
    private MnpPersonalInfoValidatorForDataToVoiceWithConfirmedIdentification mnpPersonalInfoValidatorForDataToVoiceWithConfirmedIdentification;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private DataToVoiceOrderWithMnpWithConfirmedIdentificationService dataToVoiceOrderWithMnpWithConfirmedIdentificationService;

    @Autowired
    private DataToVoiceOrderWithMnpWithConfirmedIdentificationApplyResponse dataToVoiceOrderWithMnpWithConfirmedIdentificationApplyResponse;


    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpdatatovoicewithconfirmedidentificationapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid DataToVoiceOrderWithMnpWithConfirmedIdentificationApplyRequest request, Errors errors) {

        mnpPersonalInfoValidatorForDataToVoiceWithConfirmedIdentification.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = dataToVoiceOrderWithMnpWithConfirmedIdentificationService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK(),
                request.getMnpInInitialRequestData()
        );
        return dataToVoiceOrderWithMnpWithConfirmedIdentificationApplyResponse.build(identificationReceiptNumber);
    }

}
