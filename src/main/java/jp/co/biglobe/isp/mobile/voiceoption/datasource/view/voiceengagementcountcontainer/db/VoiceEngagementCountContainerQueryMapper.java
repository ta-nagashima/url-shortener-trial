package jp.co.biglobe.isp.mobile.voiceoption.datasource.view.voiceengagementcountcontainer.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer.VoiceEngagementCount;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStatus;
import org.apache.ibatis.annotations.Param;

public interface VoiceEngagementCountContainerQueryMapper {
    public VoiceEngagementCount findByLteThreeGEngagementNumberForOrderedCount(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("voiceEngagementStatus") VoiceEngagementStatus voiceEngagementStatus);

    public VoiceEngagementCount findByLteThreeGEngagementNumberForEngagedCount(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("voiceEngagementStatus") VoiceEngagementStatus voiceEngagementStatus);

    public VoiceEngagementCount findByLteThreeGEngagementNumberForDisengagedReservedCount(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("voiceEngagementStatus") VoiceEngagementStatus voiceEngagementStatus,
            @Param("eventDate") VoiceEngagementEndDateTime eventDate);

}
