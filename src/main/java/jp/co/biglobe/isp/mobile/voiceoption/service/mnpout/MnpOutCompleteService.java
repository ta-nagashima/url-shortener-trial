package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出完了
 */

@Service
public class MnpOutCompleteService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpOutRepository mnpOutRepository;

    @Autowired
    MnpOutChargeRepository mnpOutChargeRepository;

    public void complete(EquipmentNumber equipmentNumber, MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForValid(equipmentNumber);

        ValidMnpOut beforeValidMnpOut = mnpOutRepository.findByVoiceEngagementNumberForUpdate(validVoiceEngagement.getVoiceEngagementNumber());

        ValidMnpOut validMnpOut = beforeValidMnpOut.complete(mnpOutCompletionConfirmedDate);

        mnpOutRepository.update(validMnpOut);

        mnpOutChargeRepository.register(validVoiceEngagement);
    }
}
