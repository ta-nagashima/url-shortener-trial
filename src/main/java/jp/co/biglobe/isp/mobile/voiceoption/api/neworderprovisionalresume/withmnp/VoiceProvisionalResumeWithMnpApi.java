package jp.co.biglobe.isp.mobile.voiceoption.api.neworderprovisionalresume.withmnp;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.service.neworderprovisionalresume.VoiceProvisionalResumeWithMnpService;
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
 * 仮受付復旧
 */
@Controller
public class VoiceProvisionalResumeWithMnpApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceProvisionalResumeWithMnpService voiceProvisionalResumeWithMnpService;

    @Autowired
    private VoiceProvisionalResumeWithMnpResponse voiceProvisionalResumeWithMnpResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/provisionalresumewithmnp/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceProvisionalResumeWithMnpRequest provisionalResumeRequest, Errors errors){

        alarmValidationVerifier.verify(errors);

        IdentificationReceiptNumber identificationReceiptNumber = voiceProvisionalResumeWithMnpService.update(
                provisionalResumeRequest.getLteThreeGEngagementNumberForm().getValueObject(),
                provisionalResumeRequest.getMnpInputItems()
        );

        return voiceProvisionalResumeWithMnpResponse.build(identificationReceiptNumber);
    }
}
