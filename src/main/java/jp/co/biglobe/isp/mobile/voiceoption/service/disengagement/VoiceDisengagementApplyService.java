package jp.co.biglobe.isp.mobile.voiceoption.service.disengagement;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.monthlycharge.VoiceMonthlyChargeRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageOrderDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengageReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解約予約
 */

@Service
public class VoiceDisengagementApplyService {

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;


    @Autowired
    VoiceMonthlyChargeRepository voiceMonthlyChargeRepository;

    public void disengage(EquipmentNumber equipmentNumber,
                          VoiceEngagementEndDateTime voiceEngagementEndDateTime,
                          VoiceEngagementDisengageReason voiceEngagementDisengageReason,
                          VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate) {

        ValidVoiceEngagement beforeValidVoiceEngagement
                = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(beforeValidVoiceEngagement.getVoiceEngagementNumber());

        ValidVoiceEngagement validVoiceEngagement = beforeValidVoiceEngagement.disengageWithNoMnpOut
                (voiceEngagementEndDateTime, voiceEngagementDisengageReason, voiceEngagementDisengageOrderDate, mnpOut);

        voiceEngagementRepository.update(validVoiceEngagement);

        voiceMonthlyChargeRepository.delete(voiceEngagementEndDateTime, validVoiceEngagement);

    }

}
