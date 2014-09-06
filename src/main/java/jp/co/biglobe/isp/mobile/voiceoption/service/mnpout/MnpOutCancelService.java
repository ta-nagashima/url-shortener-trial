package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutCancelReason;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出キャンセル
 */
@Service
public class MnpOutCancelService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpOutRepository mnpOutRepository;

    public void cancel(EquipmentNumber equipmentNumber, MnpOutCancelReason mnpOutCancelReason) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForValid(equipmentNumber);

        // MNP転出情報を取得
        ValidMnpOut validMnpOut = mnpOutRepository.findByVoiceEngagementNumberForUpdate(validVoiceEngagement.getVoiceEngagementNumber());

        // MNP転出情報を更新
        ValidMnpOut canceledValidMnpOut = validMnpOut.cancel(mnpOutCancelReason);

        // DBに反映して永続化
        mnpOutRepository.update(canceledValidMnpOut);

    }

}
