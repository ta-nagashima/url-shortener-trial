package jp.co.biglobe.isp.mobile.voiceoption.service.mailsend;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationResultMailSendService {

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private VoiceSendMailRepository voiceSendMailRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    public VoiceSendMailStatus send(IdentificationReceiptNumber identificationReceiptNumber) {

        // 本人確認のレコードを検索
        ValidIdentification validIdentification
                = identificationRepository.findByIdentificationReceiptNumberForValid(identificationReceiptNumber);

        return voiceSendMailRepository.sendIdentificationResultMail(
                getUserIdFromValidIdentification(validIdentification));
    }

    /**
     * IdentificationReceiptNumberからUserIdを取得する。
     *
     * ※本人確認エンティティもUserIdを持っているが、将来的には
     * 本人確認は音声契約に紐づく可能性もあるため、
     * あえて音声契約から取得するようにした。
     */
    protected UserId getUserIdFromValidIdentification(ValidIdentification validIdentification) {

        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByVoiceEngagementNumberForValid(
                validIdentification.getVoiceEngagementNumber());

        return validVoiceEngagement.getVoiceEngagementLinkage().getUserId();

    }

}
