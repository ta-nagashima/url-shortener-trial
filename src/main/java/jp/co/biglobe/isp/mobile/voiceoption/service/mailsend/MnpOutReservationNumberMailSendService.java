package jp.co.biglobe.isp.mobile.voiceoption.service.mailsend;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MnpOutReservationNumberMailSendService {

    @Autowired
    private VoiceSendMailRepository voiceSendMailRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;


    public VoiceSendMailStatus send(VoiceEngagementNumber voiceEngagementNumber, MnpReservationNumber mnpReservationNumber) {

        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByVoiceEngagementNumberForValid(voiceEngagementNumber);

        return voiceSendMailRepository.sendMnpOutReservationNumberMail(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId(),
                mnpReservationNumber
                );
    }






}
