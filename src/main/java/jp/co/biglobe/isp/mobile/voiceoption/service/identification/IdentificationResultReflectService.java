package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.IdentificationResultMailNotSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.IdentificationResultMailSendService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mailsend.IdentificationWithMnpInResultMailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationResultReflectService {


    @Autowired
    private IdentificationNgService identificationNgService;

    @Autowired
    private IdentificationOkService identificationOkService;

    @Autowired
    private IdentificationOkWithMnpInService identificationOkWithMnpInService;

    @Autowired
    private IdentificationResultMailNotSendService identificationResultMailNotSendService;

    @Autowired
    private IdentificationResultMailSendService identificationResultMailSendService;

    @Autowired
    private IdentificationWithMnpInResultMailSendService identificationWithMnpInResultMailSendService;

    public VoiceSendMailStatus reflect(
            IdentificationStatusEvent identificationStatusEvent,
            IdentificationReceiptNumber identificationReceiptNumber,
            ValidIdentificationResult validIdentificationResult,
            MnpInActivationDueDate mnpInActivationDueDate,
            MnpIn mnpIn
    ) {

        // NGのとき
        if (identificationStatusEvent == IdentificationStatusEvent.NG) {
            identificationNgService.reflect(
                    identificationReceiptNumber,
                    validIdentificationResult);

            return identificationResultMailNotSendService.send();
        }

        // OKでMNP転入ありのとき
        if (mnpIn.isExist()) {

            ValidMnpInActivationDueDate validMnpInActivationDueDate = MnpInActivationDueDateFactory.createForValid(
                    identificationStatusEvent,
                    mnpIn,
                    mnpInActivationDueDate);

            identificationOkWithMnpInService.reflect(
                    identificationReceiptNumber,
                    validIdentificationResult,
                    validMnpInActivationDueDate);

            return identificationWithMnpInResultMailSendService.send(
                    identificationReceiptNumber,
                    validMnpInActivationDueDate);
        }

        // OKでMNP転入なしのとき
        identificationOkService.reflect(identificationReceiptNumber, validIdentificationResult);

        return identificationResultMailSendService.send(identificationReceiptNumber);


    }

}
