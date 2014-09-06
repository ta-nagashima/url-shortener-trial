package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解約キャンセル
 */

@Service
public class VoiceDisengagementCancelService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private VoiceMonthlyChargeRepository voiceMonthlyChargeRepository;

    @Autowired
    private RequestEventTime requestEventTime;

    public void cancel(EquipmentNumber equipmentNumber) {

        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        ValidVoiceEngagement disengageCanceledValidVoiceEngagement
                = validVoiceEngagement.disengageCancel(requestEventTime.getDate());

        voiceEngagementRepository.update(disengageCanceledValidVoiceEngagement);

        voiceMonthlyChargeRepository.update(disengageCanceledValidVoiceEngagement);


    }
}
