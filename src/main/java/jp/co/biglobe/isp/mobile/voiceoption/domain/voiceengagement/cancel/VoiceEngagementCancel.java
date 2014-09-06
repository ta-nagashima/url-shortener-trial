package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface VoiceEngagementCancel extends CommonEntity {

    public ValidVoiceEngagementCancel cancel(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate
    );
}
