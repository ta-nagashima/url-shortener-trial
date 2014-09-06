package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutonnewordercancelapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.MnpOutApplyMailNotSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.MnpOutApplyMailSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutOnNewOrderCancelApplyService;
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
 * MNP転出(新規申込キャンセル時)
 */
@Controller
public class MnpOutOnNewOrderCancelApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutOnNewOrderCancelApplyService mnpOutOnNewOrderCancelApplyService;

    @Autowired
    private MnpOutApplyMailSendService mnpOutApplyMailSendService;

    @Autowired
    private MnpOutApplyMailNotSendService mnpOutApplyMailNotSendService;

    @Autowired
    private MnpOutOnNewOrderCancelApplyResponse mnpOutOnNewOrderCancelApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutonnewordercancelapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutOnNewOrderCancelApplyRequest mnpOutOnNewOrderCancelApplyRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の登録
        mnpOutOnNewOrderCancelApplyService.apply(
                mnpOutOnNewOrderCancelApplyRequest.getEquipmentNumberForm().getValueObject()
        );

        if(mnpOutOnNewOrderCancelApplyRequest.isSend()){
            VoiceSendMailStatus voiceSendMailStatus
                    = mnpOutApplyMailSendService.send(mnpOutOnNewOrderCancelApplyRequest.getEquipmentNumberForm().getValueObject());

            return mnpOutOnNewOrderCancelApplyResponse.build(voiceSendMailStatus);
        }

        VoiceSendMailStatus voiceSendMailStatus
                = mnpOutApplyMailNotSendService.send();

        return mnpOutOnNewOrderCancelApplyResponse.build(voiceSendMailStatus);
    }

}