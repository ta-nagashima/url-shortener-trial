package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.immediatedisengagementrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.disengagement.VoiceImmediateDisengagementReferService;
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
 * 即時解約参照
 */
@Controller
public class VoiceImmediateDisengagementReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceImmediateDisengagementReferService voiceImmediateDisengagementReferService;

    @Autowired
    private VoiceImmediateDisengagementReferResponse voiceImmediateDisengagementReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/immediatedisengagement/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invode(@Valid VoiceImmediateDisengagementReferRequest request, Errors errors) {
        alarmValidationVerifier.verify(errors);

        MnpOutStatus mnpOutStatus = voiceImmediateDisengagementReferService.refer(
                request.getEquipmentNumberForm().getValueObject()
        );
        return voiceImmediateDisengagementReferResponse.build(mnpOutStatus);
    }

}
