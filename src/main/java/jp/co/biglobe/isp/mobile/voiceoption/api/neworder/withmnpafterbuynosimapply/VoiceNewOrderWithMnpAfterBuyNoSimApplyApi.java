package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.withmnpafterbuynosimapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderWithMnpAfterBuyNoSimApplyService;
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
 * MNPあり・後からチャネル・新規申込
 */
@Controller
public class VoiceNewOrderWithMnpAfterBuyNoSimApplyApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderWithMnpAfterBuyNoSimApplyService voiceNewOrderWithMnpAfterBuyNoSimApplyService;

    @Autowired
    private VoiceNewOrderWithMnpAfterBuyNoSimApplyResponse voiceNewOrderWithMnpAfterBuyNoSimApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/neworderwithmnpafterbuynosimapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderWithMnpAfterBuyNoSimApplyRequest request,Errors errors){

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderWithMnpAfterBuyNoSimApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK(),
                request.getMnpInInitialRequestData());

        return voiceNewOrderWithMnpAfterBuyNoSimApplyResponse.build(identificationReceiptNumber);

    }

}
