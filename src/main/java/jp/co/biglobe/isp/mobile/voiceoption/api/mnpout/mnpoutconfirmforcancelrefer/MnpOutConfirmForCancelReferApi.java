package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutconfirmforcancelrefer;

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
 * MNP転出意思参照（連動キャンセル）
 */
@Controller
public class MnpOutConfirmForCancelReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutConfirmReferService mnpOutConfirmReferService;

    @Autowired
    private MnpOutConfirmForCancelReferResponse mnpOutConfirmForCancelReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutconfirmforcancel/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutConfirmForCancelReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        VoiceEngagementDetailReferContainer voiceEngagementDetailReferContainer
            = mnpOutConfirmReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return mnpOutConfirmForCancelReferResponse.build(voiceEngagementDetailReferContainer);

    }

}
