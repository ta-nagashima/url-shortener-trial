package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutconfirmfordisengagerefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutConfirmReferService;
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
 * MNP転出意思参照（連動解約）
 */
@Controller
public class MnpOutConfirmForDisengageReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutConfirmReferService mnpOutConfirmReferService;

    @Autowired
    private MnpOutConfirmForDisengageReferResponse mnpOutIntentionReferForCancelResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutconfirmfordisengage/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutConfirmForDisengageReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        VoiceEngagementDetailReferContainer voiceEngagementDetailReferContainer
            = mnpOutConfirmReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return mnpOutIntentionReferForCancelResponse.build(voiceEngagementDetailReferContainer);

    }

}
