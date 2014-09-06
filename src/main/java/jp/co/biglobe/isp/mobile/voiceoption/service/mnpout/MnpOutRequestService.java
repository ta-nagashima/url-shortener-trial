package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutRequestInfoReturnContainer;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 転出依頼
 */

@Service
public class MnpOutRequestService {

    @Autowired
    MnpOutRepository mnpOutRepository;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    public MnpOutRequestInfoReturnContainer update(VoiceEngagementNumber voiceEngagementNumber) {

        // MNP転出情報を取得
        ValidMnpOut validMnpOut = mnpOutRepository.findByVoiceEngagementNumberForUpdate(voiceEngagementNumber);

        // MNP転出情報を更新（更新できないステータスの場合はビジネス例外）
        ValidMnpOut requestedValidMnpOut = validMnpOut.changeStatusRequested(validMnpOut);

        // DBに反映して永続化
        mnpOutRepository.update(requestedValidMnpOut);

        // 契約情報を取得
        ValidVoiceEngagement voiceEngagement
                = voiceEngagementRepository.findByVoiceEngagementNumberForValid(
                requestedValidMnpOut.getVoiceEngagementNumber());

        //全情報を返却
        return new MnpOutRequestInfoReturnContainer(voiceEngagement, requestedValidMnpOut);

    }

}

