package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.db;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutItemCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MnpOutChargeQueryMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber,
            @Param("operatorId") OperatorId operatorId,
            @Param("userId") UserId userId,
            @Param("mnpOutItemCode") MnpOutItemCode mnpOutItemCode,
            @Param("mnpOutFee") MnpOutFee mnpOutFee
    );


}
