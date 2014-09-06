package jp.co.biglobe.isp.mobile.voiceoption.domain.exception;

import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationErrorStatus;

public class IdentificationInvalidStatusException extends BusinessException {

    public IdentificationInvalidStatusException(String message) {
        super(message, IdentificationErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
    }
}
