package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db;

import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpInPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.apache.ibatis.annotations.Param;

public interface MnpInPersonalInfoQueryMapper extends CommonRegisterMapper<ValidMnpInPersonalInfo> {

    public ValidMnpInPersonalInfo findByVoiceEngagementNumber(
            @Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber
    );

}
