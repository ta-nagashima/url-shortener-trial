package jp.co.biglobe.isp.mobile.voiceoption.domain.exception;

import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutErrorStatus;

/**
 * 処理の競合などにより、MnpOutのステータスを変更できないときの例外
 */
public class MnpOutInvalidStatusException extends BusinessException {

    public MnpOutInvalidStatusException(String message) {
        super(message, MnpOutErrorStatus.INVALID_STATUS, VoiceOptionAlarmIdentifier.DEFAULT);
    }
}
