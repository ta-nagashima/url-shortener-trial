package jp.co.biglobe.isp.mobile.voiceoption.service.neworder;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 新規契約ロールバック
 */

@Service
public class VoiceNewOrderRollbackService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private MnpInRepository mnpInRepository;


    public void rollback(EquipmentNumber equipmentNumber) {

        //音声オプションの削除
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        mnpInRepository.delete(validVoiceEngagement.getVoiceEngagementNumber());
        identificationRepository.delete(validVoiceEngagement.getVoiceEngagementNumber());
        voiceEngagementRepository.delete(validVoiceEngagement);

    }
}
