package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStatus;
import org.apache.ibatis.annotations.Param;

public interface VoiceEngagementMapper extends CommonRegisterMapper<ValidVoiceEngagement> {

    public ValidVoiceEngagement findByUserId(
            @Param("userId") UserId userId
    );

    public ValidVoiceEngagement findByVoiceEngagementNumber(@Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber);

    public ValidVoiceEngagement findByEquipmentNumber(@Param("equipmentNumber") EquipmentNumber equipmentNumber);

    public ValidVoiceEngagement findByLteThreeGEngagementNumberUnderValid(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("eventDate") VoiceEngagementEndDateTime eventDate,
            @Param("voiceEngagementStatusOrdered") VoiceEngagementStatus voiceEngagementStatusOrdered,
            @Param("voiceEngagementStatusEngaged") VoiceEngagementStatus voiceEngagementStatusEngaged,
            @Param("voiceEngagementStatusDisengaged") VoiceEngagementStatus voiceEngagementStatusDisengaged
    );

    public ValidVoiceEngagement findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public VoiceEngagementNumber lockByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );

    public VoiceEngagementNumber lockByEquipmentNumber(
            @Param("equipmentNumber") EquipmentNumber equipmentNumber
    );
}
