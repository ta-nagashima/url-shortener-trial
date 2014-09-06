package jp.co.biglobe.isp.mobile.voiceoption.service.mailsend;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.ValidMnpInActivationDueDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationWithMnpInResultMailSendService {

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceSendMailRepository voiceSendMailRepository;

    @Autowired
    private IdentificationResultMailSendService identificationResultMailSendService;

    @Autowired
    private MnpInRepository mnpInRepository;


    public VoiceSendMailStatus send(IdentificationReceiptNumber identificationReceiptNumber, ValidMnpInActivationDueDate validMnpInActivationDueDate) {

        // 本人確認のレコードを検索
        ValidIdentification validIdentification
                = identificationRepository.findByIdentificationReceiptNumberForValid(identificationReceiptNumber);

        // MnpInを検索（Mnp転入レコードが見つからない場合はSystemCheckExceptionにする）
        mnpInRepository.findByVoiceEngagementNumberForValid(validIdentification.getVoiceEngagementNumber());

        // UserIDを取得
        UserId userId = identificationResultMailSendService.getUserIdFromValidIdentification(validIdentification);


        return voiceSendMailRepository.sendIdentificationResultMail(userId, validMnpInActivationDueDate);
    }

}
