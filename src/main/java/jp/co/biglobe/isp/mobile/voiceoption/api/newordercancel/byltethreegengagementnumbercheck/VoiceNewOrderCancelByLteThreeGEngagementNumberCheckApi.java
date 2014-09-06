package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.byltethreegengagementnumbercheck;

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
 * 新規申込キャンセル可否チェック(LTE3G契約番号)
 */
@Controller
public class VoiceNewOrderCancelByLteThreeGEngagementNumberCheckApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private VoiceNewOrderCancelCheckService voiceNewOrderCancelCheckService;

    @Autowired
    private VoiceNewOrderCancelByLteThreeGEngagementNumberCheckResponse voiceNewOrderCancelByLteThreeGEngagementNumberCheckResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/newordercancelbyltethreegengagementnumber/check",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid VoiceNewOrderCancelByLteThreeGEngagementNumberCheckRequest request, Errors errors) {
        alarmValidationVerifier.verify(errors);

        NewOrderCancelCheckStatusContainer newOrderCancelCheckStatusContainer = voiceNewOrderCancelCheckService.check(
                request.getLteThreeGEngagementNumberForm().getValueObject()
        );
        return voiceNewOrderCancelByLteThreeGEngagementNumberCheckResponse.build(newOrderCancelCheckStatusContainer);

    }
}
