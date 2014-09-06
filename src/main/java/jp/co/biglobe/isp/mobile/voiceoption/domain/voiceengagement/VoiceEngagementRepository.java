package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;

public interface VoiceEngagementRepository {

    public void insert(ValidVoiceEngagement validVoiceEngagement);

    public void update(ValidVoiceEngagement validVoiceEngagement);

    public void delete(ValidVoiceEngagement validVoiceEngagement);

    public VoiceEngagement findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber);

    public ValidVoiceEngagement findByVoiceEngagementNumberForValid(VoiceEngagementNumber voiceEngagementNumber);

    public VoiceEngagement findByVoiceEngagementNumber(Identification identification);

    public VoiceEngagement findByUserId(UserId userId);

    public VoiceEngagement findByEquipmentNumber(EquipmentNumber equipmentNumber);

    public ValidVoiceEngagement findByEquipmentNumberForValid(EquipmentNumber equipmentNumber);

    public ValidVoiceEngagement findByEquipmentNumberForUpdate(EquipmentNumber equipmentNumber);

    public VoiceEngagement findByLteThreeGEngagementNumberForEnable(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public VoiceEngagement findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);
}
