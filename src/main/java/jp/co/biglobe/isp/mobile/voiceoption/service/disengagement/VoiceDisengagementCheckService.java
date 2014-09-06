package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.DisengagementCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.DisengagementCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndDisengagementCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceDisengagementCheckService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private VoiceDisengagementChargeRepository voiceDisengagementChargeRepository;

    /**
     * LTE3G契約番号による確認
     */
    public DisengagementCheckStatusContainer check(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);


        DisengagementCheckStatus disengagementCheckStatus = voiceEngagement.verifyDisengagementCheckStatus(mnpOut);
        MnpOutAndDisengagementCheckStatus mnpOutAndDisengagementCheckStatus = voiceEngagement.verifyMnpOutAndDisengagementCheckStatus(mnpOut);

        return new DisengagementCheckStatusContainer(disengagementCheckStatus, mnpOutAndDisengagementCheckStatus);
    }

    /**
     * 機器番号による確認
     */
    public DisengagementCheckStatusContainer check(EquipmentNumber equipmentNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);


        DisengagementCheckStatus disengagementCheckStatus = voiceEngagement.verifyDisengagementCheckStatus(mnpOut);
        MnpOutAndDisengagementCheckStatus mnpOutAndDisengagementCheckStatus = voiceEngagement.verifyMnpOutAndDisengagementCheckStatus(mnpOut);

        return new DisengagementCheckStatusContainer(disengagementCheckStatus, mnpOutAndDisengagementCheckStatus);
    }

    /**
     * LTE3G契約番号による確認
     */
    public DisengagementCharge getPenaltyCharge(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        // 契約解除料の取得
        return voiceDisengagementChargeRepository.findByVoiceEngagementNumberUnderValid(lteThreeGEngagementNumber);
    }

    /**
     * 機器番号による確認
     */
    public DisengagementCharge getPenaltyCharge(EquipmentNumber equipmentNumber){

        // 契約解除料の取得
        return voiceDisengagementChargeRepository.findByEquipmentNumber(equipmentNumber);
    }

}
