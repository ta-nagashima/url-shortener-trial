package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.mnpoutcompletionanddisengagementapply;

import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceMnpOutCompletionAndDisengagementService;
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
 * MNP転出確定兼即時解約
 */
@Controller
public class VoiceMnpOutCompletionAndDisengagementApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceMnpOutCompletionAndDisengagementService voiceMnpOutCompletionAndDisengagementService;

    @Autowired
    private VoiceMnpOutCompletionAndDisengagementApplyResponse voiceMnpOutCompletionAndDisengagementApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutcompletionanddisengagementapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceMnpOutCompletionAndDisengagementApplyRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の登録
        voiceMnpOutCompletionAndDisengagementService.disengage(
                request.getEquipmentNumberForm().getValueObject(),
                request.getMnpOutCompletionConfirmedDateForm().getValueObject(),
                request.getVoiceEngagementEndDateTimeForm().getValueObject(),
                request.getVoiceEngagementDisengageOrderDateForm().getValueObject()
        );

        return voiceMnpOutCompletionAndDisengagementApplyResponse.build();
    }

}
