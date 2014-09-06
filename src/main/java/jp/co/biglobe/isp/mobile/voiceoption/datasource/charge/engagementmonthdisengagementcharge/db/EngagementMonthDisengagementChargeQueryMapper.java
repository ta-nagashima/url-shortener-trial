package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.engagementmonthdisengagementcharge.db;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.EngagementMonthDisengagementItemCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge.ValidEngagementMonthDisengagementCharge;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface EngagementMonthDisengagementChargeQueryMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber,
            @Param("operatorId") OperatorId operatorId,
            @Param("userId") UserId userId,
            @Param("engagementMonthDisengagementItemCode") EngagementMonthDisengagementItemCode engagementMonthDisengagementItemCode,
            @Param("engagementMonthDisengagementFee")EngagementMonthDisengagementFee engagementMonthDisengagementFee
    );

    public void insertState(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber,
            @Param("operatorId") OperatorId operatorId,
            @Param("userId") UserId userId,
            @Param("engagementMonthDisengagementItemCode") EngagementMonthDisengagementItemCode engagementMonthDisengagementItemCode,
            @Param("engagementMonthDisengagementFee")EngagementMonthDisengagementFee engagementMonthDisengagementFee,
            @Param("eventDate") Date eventDate
    );

    public ValidEngagementMonthDisengagementCharge findByVoiceEngagementNumber(@Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber);
}
