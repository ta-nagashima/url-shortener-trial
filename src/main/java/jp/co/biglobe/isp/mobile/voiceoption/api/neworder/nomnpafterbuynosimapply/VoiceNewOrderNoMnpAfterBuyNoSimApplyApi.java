package jp.co.biglobe.isp.mobile.voiceoption.api.neworder.nomnpafterbuynosimapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworder.VoiceNewOrderNoMnpAfterBuyNoSimApplyService;
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
 * 後からスキームSimなしMnpなし新規申込
 */
@Controller
public class VoiceNewOrderNoMnpAfterBuyNoSimApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderNoMnpAfterBuyNoSimApplyService voiceNewOrderNoMnpAfterBuyNoSimApplyService;

    @Autowired
    private VoiceNewOrderNoMnpAfterBuyNoSimApplyResponse voiceNewOrderNoMnpAfterBuyNoSimApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordernomnpafterbuynosimapply/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderNoMnpAfterBuyNoSimApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceNewOrderNoMnpAfterBuyNoSimApplyService.newApply(
                request.getVoiceEngagementInitialData(),
                request.getIdentificationInitialRequestDataForOK());

        return voiceNewOrderNoMnpAfterBuyNoSimApplyResponse.build(identificationReceiptNumber);

    }
}
