package jp.co.biglobe.isp.mobile.voiceoption.service.newordercancel;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancelOrderDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出完了兼新規申込即時キャンセル
 */

@Service
public class VoiceMnpOutCompletionAndNewOrderCancelService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpOutRepository mnpOutRepository;

    @Autowired
    VoiceImmediateCancelService voiceImmediateCancelService;

    public void cancel(EquipmentNumber equipmentNumber, MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate, VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate) {

        ValidVoiceEngagement beforeValidVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);
        ValidMnpOut beforeValidMnpOut =
                mnpOutRepository.findByVoiceEngagementNumberForUpdate(beforeValidVoiceEngagement.getVoiceEngagementNumber());

        ValidMnpOut validMnpOut = beforeValidMnpOut.complete(mnpOutCompletionConfirmedDate);
        ValidVoiceEngagement validVoiceEngagement =
                beforeValidVoiceEngagement.cancelWithMnpOutComplete(voiceEngagementCancelOrderDate, validMnpOut);

        // 商品契約はロールバックできないので、一番最後に処理する
        mnpOutRepository.update(validMnpOut);
        voiceEngagementRepository.update(validVoiceEngagement);

        // キャンセルのため、MNP転出手数料はとらないよ。


    }
}
