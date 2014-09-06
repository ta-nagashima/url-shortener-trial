package jp.co.biglobe.isp.mobile.voiceoption.service.mailsend;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import org.springframework.stereotype.Service;

@Service
public class IdentificationResultMailNotSendService {

    public VoiceSendMailStatus send() {

        return VoiceSendMailStatus.UNNECESSARY;
    }

}
