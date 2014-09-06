package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.ValidEngagementMonthDisengagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 契約月解約課金
 */
@Service
public class VoiceEngagementMonthDisengagementChargeApplyService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    EngagementMonthDisengagementChargeRepository engagementMonthDisengagementChargeRepository;

    public void charge(EquipmentNumber equipmentNumber) {

        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        ValidEngagementMonthDisengagement validEngagementMonthDisengagement = ValidEngagementMonthDisengagement.create(validVoiceEngagement);

        EngagementMonthDisengagementCharge engagementMonthDisengagementCharge = engagementMonthDisengagementChargeRepository.findByVoiceEngagement(validVoiceEngagement.getVoiceEngagementNumber());

        validEngagementMonthDisengagement.check(engagementMonthDisengagementCharge);

        engagementMonthDisengagementChargeRepository.register(validVoiceEngagement);
    }
}
