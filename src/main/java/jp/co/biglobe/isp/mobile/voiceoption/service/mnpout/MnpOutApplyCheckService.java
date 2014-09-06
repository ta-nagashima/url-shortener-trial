package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutApplyCheckStatusContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutApplyCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutApplyCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MnpOutApplyCheckService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpInRepository mnpInRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private  IdentificationRepository identificationRepository;

    public MnpOutApplyCheckStatusContainer check(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        // 転入情報の取得
        MnpIn mnpIn = mnpInRepository.findByVoiceEngagementNumber(voiceEngagement);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);

        // 本人確認状態の取得
        Identification identification = identificationRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        MnpOutApplyCheckStatus mnpOutApplyCheckStatus = voiceEngagement.verifyMnpOutApplyCheckStatus(identification, mnpIn, mnpOut);
        MnpOutApplyCancelCheckStatus mnpOutApplyCancelCheckStatus = voiceEngagement.verifyMnpOutApplyCancelCheckStatus(identification, mnpIn, mnpOut);

        return new MnpOutApplyCheckStatusContainer(mnpOutApplyCheckStatus, mnpOutApplyCancelCheckStatus);
    }

}
