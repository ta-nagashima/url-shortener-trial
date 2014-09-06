package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpapplywithconfirmedidentification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderNoMnpWithConfirmedIdentificationApplyService;
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
 * MNPなし新規申込（本人確認不要）
 */
@Controller
public class VoiceNewOrderNoMnpWIthConfirmedIdentificationApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderNoMnpWithConfirmedIdentificationApplyService voiceNewOrderNoMnpWithConfirmedIdentificationApplyService;

    @Autowired
    private VoiceNewOrderNoMnpWithConfirmedIdentificationApplyResponse voiceNewOrderNoMnpApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpwithconfirmedidentificationapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderNoMnpWithConfirmedIdentificationApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderNoMnpWithConfirmedIdentificationApplyService.newOrder(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK()
        );

        return voiceNewOrderNoMnpApplyResponse.build(identificationReceiptNumber);

    }

}
