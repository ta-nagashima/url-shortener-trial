package jp.co.biglobe.isp.mobile.voiceoption.domain.exception;

import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementErrorStatus;

public class VoiceEngagementInvalidStatusException extends BusinessException {

    public VoiceEngagementInvalidStatusException(String message) {
        super(message, VoiceEngagementErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
    }
}
