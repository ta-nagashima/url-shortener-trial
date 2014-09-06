package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetailrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailReferService;
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
 * 契約情報参照
 */
@Controller
public class VoiceEngagementDetailReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceEngagementDetailReferService voiceEngagementDetailReferService;

    @Autowired
    private VoiceEngagementDetailReferResponse voiceEngagementDetailReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/engagementdetail/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceEngagementDetailReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        VoiceEngagementDetailReferContainer voiceEngagementDetailReferContainer =
                voiceEngagementDetailReferService.refer(request.getEquipmentNumberForm().getValueObject());

        return voiceEngagementDetailReferResponse.build(voiceEngagementDetailReferContainer);

    }

}
