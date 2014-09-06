package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.immediatecancel;

import jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel.VoiceImmediateCancelService;
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
 * 即時キャンセル
 */

@Controller
public class VoiceImmediateCancelApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceImmediateCancelService immediateCancellationService;

    @Autowired
    private VoiceImmediateCancelResponse voiceImmediateCancelResponse;

    // 契約ステータスをキャンセル
    @RequestMapping(value = "/voiceoption/outside/skip/newordercancel/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceImmediateCancelRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 音声契約オプションのステータスをキャンセルに更新
        immediateCancellationService.cancel(
                request.getEquipmentNumberForm().getValueObject(),
                request.getVoiceEngagementCancelReasonForm().getValueObject(),
                request.getVoiceEngagementCancelOrderDateForm().getValueObject()
        );

        return voiceImmediateCancelResponse.build();
    }

}
