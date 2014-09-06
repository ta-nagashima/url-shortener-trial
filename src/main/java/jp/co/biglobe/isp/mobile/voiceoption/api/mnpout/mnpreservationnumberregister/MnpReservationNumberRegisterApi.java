package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpreservationnumberregister;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.MnpOutReservationNumberMailSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpReservationNumberRegisterService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MnpReservationNumberRegisterApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpReservationNumberRegisterService mnpReservationNumberRegisterService;

    @Autowired
    private MnpReservationNumberRegisterResponse mnpReservationNumberRegisterResponse;

    @Autowired
    private MnpOutReservationNumberMailSendService mnpOutReservationNumberMailSendService;

    @RequestMapping(value = "/voiceoption/skip/mnpreservationnumberregister/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpReservationNumberRegisterRequest request, Errors errors) {
        alarmValidationVerifier.verify(errors);

        mnpReservationNumberRegisterService.register(
                request.getVoiceEngagementNumberForm().getValueObject(),
                request.getMnpReservationNumberForm().getValueObject(),
                request.getExpireDateForm().getValueObject(),
                request.getExecutionDateForm().getValueObject(),
                request.getOperatorIdForm().getValueObject()
        );

        // メール送信
        VoiceSendMailStatus voiceSendMailStatus
                = mnpOutReservationNumberMailSendService.send(
                request.getVoiceEngagementNumberForm().getValueObject(),
                request.getMnpReservationNumberForm().getValueObject()
        );

        return mnpReservationNumberRegisterResponse.build(voiceSendMailStatus);
    }

}
