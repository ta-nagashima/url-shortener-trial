package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageOrderDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出完了兼新規申込即時キャンセル
 */

@Service
public class VoiceMnpOutCompletionAndDisengagementService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpOutRepository mnpOutRepository;

    @Autowired
    MnpOutChargeRepository mnpOutChargeRepository;

    @Autowired
    VoiceMonthlyChargeRepository voiceMonthlyChargeRepository;

    public void disengage(
            EquipmentNumber equipmentNumber,
            MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate,
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate) {

        ValidVoiceEngagement beforeValidVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);
        ValidMnpOut beforeValidMnpOut =
                mnpOutRepository.findByVoiceEngagementNumberForUpdate(beforeValidVoiceEngagement.getVoiceEngagementNumber());

        ValidMnpOut validMnpOut = beforeValidMnpOut.complete(mnpOutCompletionConfirmedDate);
        ValidVoiceEngagement validVoiceEngagement =
                beforeValidVoiceEngagement.disengageWithMnpOutComplete(
                        voiceEngagementEndDateTime, voiceEngagementDisengageOrderDate, validMnpOut);

        // 商品契約はロールバックできないので、一番最後に処理する
        mnpOutRepository.update(validMnpOut);
        voiceEngagementRepository.update(validVoiceEngagement);

        voiceMonthlyChargeRepository.delete(voiceEngagementEndDateTime, validVoiceEngagement);
        mnpOutChargeRepository.register(validVoiceEngagement);


    }
}
