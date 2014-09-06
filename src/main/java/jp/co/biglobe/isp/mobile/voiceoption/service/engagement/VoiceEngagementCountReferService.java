package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCountContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 音声オプション契約数参照
 */
@Service
public class VoiceEngagementCountReferService {

    //音声オプション契約数DB参照オブジェクトの宣言
    @Autowired
    private VoiceEngagementCountContainerRepository voiceEngagementCountContainerRepository;

    //音声オプション契約数コンテナを返却するメソッドを実装
    public VoiceEngagementCountContainer refer(LteThreeGEngagementNumber lteThreeGEngagementNumber){
        return voiceEngagementCountContainerRepository.findByLteThreeGEngagementNumberForCount(lteThreeGEngagementNumber);

    }

}
