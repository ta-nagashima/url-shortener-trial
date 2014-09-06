package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.mnpoutcompletionandnewordercancel;

import jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel.VoiceMnpOutCompletionAndNewOrderCancelService;
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
 * MNP転出完了兼新規申込即時キャンセル
 */
@Controller
public class VoiceMnpOutCompletionAndNewOrderCancelApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceMnpOutCompletionAndNewOrderCancelService voiceMnpOutCompletionAndNewOrderCancelService;

    @Autowired
    private VoiceMnpOutCompletionAndNewOrderCancelResponse voiceMnpOutCompletionAndNewOrderCancelResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutcompletionandnewordercancel/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceMnpOutCompletionAndNewOrderCancelRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の登録
        voiceMnpOutCompletionAndNewOrderCancelService.cancel(
                request.getEquipmentNumberForm().getValueObject(),
                request.getMnpOutCompletionConfirmedDateForm().getValueObject(),
                request.getVoiceEngagementCancelOrderDateForm().getValueObject()
        );

        return voiceMnpOutCompletionAndNewOrderCancelResponse.build();
    }

}
