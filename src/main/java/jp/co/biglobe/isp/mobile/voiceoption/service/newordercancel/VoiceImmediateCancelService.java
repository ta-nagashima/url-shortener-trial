package jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancelOrderDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancelReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 即時キャンセル
 */

@Service
public class VoiceImmediateCancelService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    public void cancel(
            EquipmentNumber equipmentNumber,
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate) {

        //音声オプション契約の更新 →　キャンセル
        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        // 転出情報の取得
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        ValidVoiceEngagement canceledValidVoiceEngagement
                = validVoiceEngagement.cancelWithNoMnpOut(voiceEngagementCancelReason, voiceEngagementCancelOrderDate, mnpOut);

        voiceEngagementRepository.update(canceledValidVoiceEngagement);

    }
}
