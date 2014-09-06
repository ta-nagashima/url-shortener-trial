package jp.co.biglobe.isp.mobile.voiceoption.api.neworderprovisionalresume.nomnp;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworderprovisionalresume.VoiceProvisionalResumeNoMnpService;
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
 * 仮受付復旧_MNPなし
 */
@Controller
public class VoiceProvisionalResumeNoMnpApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceProvisionalResumeNoMnpService voiceProvisionalResumeNoMnpService;

    @Autowired
    private VoiceProvisionalResumeNoMnpResponse voiceProvisionalResumeNoMnpResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/provisionalresumenomnp/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceProvisionalResumeNoMnpRequest voiceProvisionalResumeNoMnpRequest, Errors errors){

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceProvisionalResumeNoMnpService.update(
                voiceProvisionalResumeNoMnpRequest.getLteThreeGEngagementNumberForm().getValueObject()
        );

        return voiceProvisionalResumeNoMnpResponse.build(identificationReceiptNumber);
    }
}
