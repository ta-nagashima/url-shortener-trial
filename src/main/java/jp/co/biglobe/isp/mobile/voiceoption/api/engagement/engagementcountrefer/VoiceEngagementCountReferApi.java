package jp.co.biglobe.isp.mobile.voiceoption.api.engagement.engagementcountrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.engagement.VoiceEngagementCountReferService;
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
 * 音声オプション契約件数参照
 */
@Controller
public class VoiceEngagementCountReferApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    //音声オプション契約件数参照サービスの宣言
    @Autowired
    private VoiceEngagementCountReferService voiceEngagementCountReferService;

    //レスポンスの宣言
    @Autowired
    private VoiceEngagementCountReferResponse voiceEngagementCountReferResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/engagementcount/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceEngagementCountReferRequest request,Errors errors){
        alarmValidationVerifier.verify(errors);

        VoiceEngagementCountContainer voiceEngagementCountContainer = voiceEngagementCountReferService.refer
                (request.getLteThreeGEngagementNumberForm().getValueObject());

        return voiceEngagementCountReferResponse.build(voiceEngagementCountContainer);
    }

}
