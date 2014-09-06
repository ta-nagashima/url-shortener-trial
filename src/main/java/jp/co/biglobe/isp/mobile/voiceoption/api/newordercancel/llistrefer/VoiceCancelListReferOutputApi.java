package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.llistrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceNewOrderCancelListReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel.VoiceNewOrderCancelListReferService;
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
 * Created by takaosakamoto on 2014/07/11.
 * 本人確認未実施自動キャンセル
 */
@Controller
class VoiceCancelListReferOutputApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderCancelListReferService voiceNewOrderCancelListReferService;

    @Autowired
    private VoiceCancelListReferOutputResponse voiceCancelListReferOutputResponse;


    @RequestMapping(value = "/voiceoption/skip/newordercancellist/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid VoiceCancelListReferOutputRequest request, Errors errors) {

        // バリデート
        alarmValidationVerifier.verify(errors);

        // サービス
        VoiceNewOrderCancelListReferContainer outputted = voiceNewOrderCancelListReferService.refer(request.getVoiceEngagementNumberForm().getValueObject());

        return voiceCancelListReferOutputResponse.build(outputted);
    }
}
