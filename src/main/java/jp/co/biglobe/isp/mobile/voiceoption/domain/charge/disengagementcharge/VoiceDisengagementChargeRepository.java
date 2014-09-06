package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;

public interface VoiceDisengagementChargeRepository {

    public void register(ValidVoiceEngagement validVoiceEngagement);

    public DisengagementCharge findByVoiceEngagementNumberUnderValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public DisengagementCharge findByEquipmentNumber(EquipmentNumber equipmentNumber);

    public DisengagementCharge findByVoiceEngagement(VoiceEngagement voiceEngagement);

}
