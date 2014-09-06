package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.VoiceMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementStatus;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MnpInQueryMapper extends CommonRegisterMapper<ValidMnpIn> {

    public void insertUpdateEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("validMnpIn") ValidMnpIn validMnpIn
    );

    public ValidMnpIn findByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );

    public ValidMnpIn findByIdentificationReceiptNumber(
            @Param("identificationReceiptNumber") IdentificationReceiptNumber identificationReceiptNumber
    );

    public List<ValidMnpIn> findByVoiceMsisdnUnderValidForAll(
            @Param("voiceMsisdn") VoiceMsisdn voiceMsisdn,
            @Param("eventDate") VoiceEngagementEndDateTime eventDate,
            @Param("voiceEngagementStatusOrdered") VoiceEngagementStatus voiceEngagementStatusOrdered,
            @Param("voiceEngagementStatusEngaged") VoiceEngagementStatus voiceEngagementStatusEngaged,
            @Param("voiceEngagementStatusDisengaged") VoiceEngagementStatus voiceEngagementStatusDisengaged
    );

    public List<ValidMnpIn> findByBiglobeDenwaMsisdnUnderValidForAll(
            @Param("biglobeDenwaMsisdn") BiglobeDenwaMsisdn biglobeDenwaMsisdn,
            @Param("eventDate") VoiceEngagementEndDateTime eventDate,
            @Param("voiceEngagementStatusOrdered") VoiceEngagementStatus voiceEngagementStatusOrdered,
            @Param("voiceEngagementStatusEngaged") VoiceEngagementStatus voiceEngagementStatusEngaged,
            @Param("voiceEngagementStatusDisengaged") VoiceEngagementStatus voiceEngagementStatusDisengaged
    );

    public ValidMnpIn findByUserId(
            @Param("userId") UserId userId
    );

    public VoiceEngagementNumber lockByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );
}
