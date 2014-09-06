package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.byequipmentnumbercheck;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.NewOrderCancelCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel.VoiceNewOrderCancelCheckService;
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
 * 新規申込キャンセル可否チェック(機器番号)
 */
@Controller
public class VoiceNewOrderCancelByEquipmentNumberCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderCancelCheckService voiceNewOrderCancelCheckService;

    @Autowired
    private VoiceNewOrderCancelByEquipmentNumberCheckResponse voiceNewOrderCancelByEquipmentNumberCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordercancelbyequipmentnumber/check",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderCancelByEquipmentNumberCheckRequest request, Errors errors) {
        alarmValidationVerifier.verify(errors);

        NewOrderCancelCheckStatusContainer newOrderCancelCheckStatusContainer = voiceNewOrderCancelCheckService.check(
                request.getEquipmentNumberForm().getValueObject()
        );
        return voiceNewOrderCancelByEquipmentNumberCheckResponse.build(newOrderCancelCheckStatusContainer);

    }
}
