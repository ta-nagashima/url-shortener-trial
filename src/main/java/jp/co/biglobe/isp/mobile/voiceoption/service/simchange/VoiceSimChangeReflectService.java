package jp.co.biglobe.isp.mobile.voiceoption.service.simchange;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SIMの変更完了
 */
@Service
public class VoiceSimChangeReflectService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    public void update(EquipmentNumber oldNumber, EquipmentNumber newNumber) {

        ValidVoiceEngagement validVoiceEngagement = createValidVoiceEngagement(oldNumber, newNumber);

        voiceEngagementRepository.update(validVoiceEngagement);
    }

    private ValidVoiceEngagement createValidVoiceEngagement(EquipmentNumber oldNumber, EquipmentNumber newNumber){

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(oldNumber);

        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        return validVoiceEngagement.changeEquipmentNumber(newNumber, mnpOut);

    }
}
