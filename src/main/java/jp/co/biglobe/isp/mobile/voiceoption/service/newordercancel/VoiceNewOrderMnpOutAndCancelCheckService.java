package jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndNewOrderCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceNewOrderMnpOutAndCancelCheckService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private VoiceDisengagementChargeRepository voiceDisengagementChargeRepository;

    public MnpOutAndNewOrderCancelCheckStatus check(EquipmentNumber equipmentNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);

        // 転入情報の取得
        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        return voiceEngagement.verifyNewOrderMnpOutAndCancelCheckStatus(mnpIn, mnpOut);
    }

}
