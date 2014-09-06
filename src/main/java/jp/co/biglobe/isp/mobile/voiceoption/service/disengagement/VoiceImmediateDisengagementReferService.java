package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 即時解約参照
 */
@Service
public class VoiceImmediateDisengagementReferService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    public MnpOutStatus refer(EquipmentNumber equipmentNumber){

        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);
        return mnpOut.getMnpOutStatus();
    }

}
