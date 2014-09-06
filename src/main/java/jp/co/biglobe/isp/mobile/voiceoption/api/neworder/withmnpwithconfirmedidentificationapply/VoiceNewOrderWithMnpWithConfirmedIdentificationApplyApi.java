package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpwithconfirmedidentificationapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderWithMnpWithConfirmedIdentificationApplyService;
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
 * MNPあり新規申込（本人確認不要）
 */
@Controller
public class VoiceNewOrderWithMnpWithConfirmedIdentificationApplyApi {

    @Autowired
    private MnpPersonalInfoValidatorForVoiceNewOrderWithConfirmedIdentification mnpPersonalInfoValidator;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderWithMnpWithConfirmedIdentificationApplyService voiceNewOrderWithMnpWithConfirmedIdentificationApplyService;

    @Autowired
    private VoiceNewOrderWithMnpWithConfirmedIdentificationApplyResponse voiceNewOrderWithMnpWithConfirmedIdentificationApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpwithconfirmedidentificationapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderWithMnpWithConfirmedIdentificationApplyRequest request, Errors errors) {

        mnpPersonalInfoValidator.validate(request, errors);
        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderWithMnpWithConfirmedIdentificationApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK(),
                request.getMnpInInitialRequestData()
        );

        return voiceNewOrderWithMnpWithConfirmedIdentificationApplyResponse.build(identificationReceiptNumber);

    }

}
