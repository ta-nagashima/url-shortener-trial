package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface VoiceEngagementDisengage extends CommonEntity {

    public ValidVoiceEngagementDisengage disengage(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementDisengageReason voiceEngagementDisengageReason,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate
    );
}
