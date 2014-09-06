package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutapply;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.MnpOutApplyMailNotSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.MnpOutApplyMailSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutApplyService;
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
 * MNP転出申込
 *
 * 転出状態が REQUEST_WAITINGのMNP転出情報が登録される
 * この後バッチ処理が走り、MNP転出依頼が行われる
 */
@Controller
public class MnpOutApplyApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutApplyService mnpOutApplyService;

    @Autowired
    private MnpOutApplyMailSendService mnpOutApplyMailSendService;

    @Autowired
    private MnpOutApplyMailNotSendService mnpOutApplyMailNotSendService;

    @Autowired
    private MnpOutApplyResponse mnpOutApplyResponse;

    @RequestMapping(value = "/voiceoption/outside/skip/mnpoutapply/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutApplyRequest mnpOutApplyRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の登録
        mnpOutApplyService.apply(
                mnpOutApplyRequest.getEquipmentNumberForm().getValueObject(),
                mnpOutApplyRequest.getMnpOutMsisdnForm().getValueObject()
        );

        if(mnpOutApplyRequest.isSend()){
            VoiceSendMailStatus voiceSendMailStatus
                    = mnpOutApplyMailSendService.send(mnpOutApplyRequest.getEquipmentNumberForm().getValueObject());

            return mnpOutApplyResponse.build(voiceSendMailStatus);
        }

        VoiceSendMailStatus voiceSendMailStatus
                = mnpOutApplyMailNotSendService.send();

        return mnpOutApplyResponse.build(voiceSendMailStatus);
    }

}