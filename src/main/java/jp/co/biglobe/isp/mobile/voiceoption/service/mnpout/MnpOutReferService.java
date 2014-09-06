package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MnpOutReferService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    public MnpOut refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        // 音声契約の取得
        VoiceEngagement voiceEngagement = voiceEngagementRepository.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        // 転出情報の返却
        return mnpOutRepository.findByVoiceEngagementNumber(voiceEngagement);
    }
}
