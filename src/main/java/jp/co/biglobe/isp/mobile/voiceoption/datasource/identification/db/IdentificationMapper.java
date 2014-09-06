package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.apache.ibatis.annotations.Param;

public interface IdentificationMapper extends CommonRegisterMapper<ValidIdentification> {

    public ValidIdentification findByIdentificationReceiptNumber(
            @Param("identificationReceiptNumber") IdentificationReceiptNumber identificationReceiptNumber
    );

    public ValidIdentification findByUserId(
            @Param("userId") UserId userId
    );

    public ValidIdentification findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGContractNumber
    );

    public ValidIdentification findByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );

    public IdentificationReceiptNumber lockByIdentificationReceiptNumber(
            @Param("identificationReceiptNumber") IdentificationReceiptNumber identificationReceiptNumber
    );

    public IdentificationReceiptNumber lockByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGContractNumber
    );

    public IdentificationReceiptNumber lockByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );

}
