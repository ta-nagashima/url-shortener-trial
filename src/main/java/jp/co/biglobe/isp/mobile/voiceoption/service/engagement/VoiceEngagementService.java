package jp.co.biglobe.isp.mobile.voiceoption.service.engagement;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.VoiceDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStartDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceEngagementService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private VoiceMonthlyChargeRepository voiceMonthlyChargeRepository;

    @Autowired
    private VoiceDisengagementChargeRepository voiceDisengagementChargeRepository;

    public void engage(EquipmentNumber equipmentNumber,
                       VoiceEngagementStartDate voiceEngagementStartDate){

        //音声オプション契約の更新
        ValidVoiceEngagement validVoiceEngagement
                = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);
        ValidVoiceEngagement engagedValidVoiceEngagement
                = validVoiceEngagement.engaged(voiceEngagementStartDate);
        voiceEngagementRepository.update(engagedValidVoiceEngagement);


        //月額課金の開始
        voiceMonthlyChargeRepository.register(engagedValidVoiceEngagement);

        //契約解除料の登録
        voiceDisengagementChargeRepository.register(engagedValidVoiceEngagement);
    }
}
