package jp.co.biglobe.isp.mobile.voiceoption.service.mailsend;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MnpOutApplyMailSendService {

    @Autowired
    private VoiceSendMailRepository voiceSendMailRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;


    public VoiceSendMailStatus send(EquipmentNumber equipmentNumber) {

        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByEquipmentNumberForValid(equipmentNumber);

        return voiceSendMailRepository.sendMnpOutRequestMail(
                validVoiceEngagement.getVoiceEngagementLinkage().getUserId());
    }






}
