package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementdetaillistrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementdetailcontainer.VoiceEngagementDetailContainerList;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementDetailListReferService;
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
 * 契約情報一覧参照
 */
@Controller
public class VoiceEngagementDetailListReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceEngagementDetailListReferService voiceEngagementDetailListReferService;

    @Autowired
    private VoiceEngagementDetailListReferResponse voiceEngagementDetailListReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/engagementdetaillist/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceEngagementDetailListReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        VoiceEngagementDetailContainerList voiceEngagementDetailContainerList =
                voiceEngagementDetailListReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return voiceEngagementDetailListReferResponse.build(voiceEngagementDetailContainerList);

    }



}
