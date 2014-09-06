package jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.NewOrderCancelCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.NewOrderCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndNewOrderCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceNewOrderCancelCheckService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private VoiceDisengagementChargeRepository voiceDisengagementChargeRepository;

    /**
     * LTE3G契約番号による確認
     *
     * @param lteThreeGEngagementNumber
     * @return
     */
    public NewOrderCancelCheckStatusContainer check(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        // 転入情報の取得
        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        NewOrderCancelCheckStatus newOrderCancelCheckStatus = voiceEngagement.verifyNewOrderCancelCheckStatus(mnpIn, mnpOut);
        MnpOutAndNewOrderCancelCheckStatus mnpOutAndNewOrderCancelCheckStatus = voiceEngagement.verifyNewOrderMnpOutAndCancelCheckStatus(mnpIn, mnpOut);

        return new NewOrderCancelCheckStatusContainer(newOrderCancelCheckStatus, mnpOutAndNewOrderCancelCheckStatus);
    }

    /**
     * 機器番号による確認
     *
     * @param equipmentNumber
     * @return
     */
    public NewOrderCancelCheckStatusContainer check(EquipmentNumber equipmentNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByEquipmentNumber(equipmentNumber);

        // 転入情報の取得
        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        NewOrderCancelCheckStatus newOrderCancelCheckStatus = voiceEngagement.verifyNewOrderCancelCheckStatus(mnpIn, mnpOut);
        MnpOutAndNewOrderCancelCheckStatus mnpOutAndNewOrderCancelCheckStatus = voiceEngagement.verifyNewOrderMnpOutAndCancelCheckStatus(mnpIn, mnpOut);

        return new NewOrderCancelCheckStatusContainer(newOrderCancelCheckStatus, mnpOutAndNewOrderCancelCheckStatus);
    }

}
