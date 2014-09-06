package jp.co.biglobe.isp.mobile.voiceoption.service.simchange;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.SimChangeCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceSimChangeCheckService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    // SIMサイズ変更可否
    public SimChangeCheckStatus check(EquipmentNumber equipmentNumber) {

        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);

        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        return voiceEngagement.verifySimChange(mnpOut);

    }
}
