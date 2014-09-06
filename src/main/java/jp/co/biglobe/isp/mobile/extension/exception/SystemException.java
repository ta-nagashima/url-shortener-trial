package jp.co.biglobe.isp.mobile.extension.exception;

import jp.co.biglobe.lib.publication.alarmidentifier.AlarmIdentifier;
import jp.co.biglobe.lib.publication.exception.type.AlarmException;
import jp.co.biglobe.lib.publication.exception.type.HiosException;

/**
 * システムチェック例外
 */
public class SystemException extends HiosException {
    /**
     * コンストラクタ
     *
     * @param message エラーメッセージ
     * @param alarmIdentifier アラーム識別子
     */
    public SystemException(final String message, final AlarmIdentifier alarmIdentifier) {
        super(message, alarmIdentifier);
    }

    /**
     * コンストラクタ
     *
     * @param message エラーメッセージ
     * @param cause スロー元例外
     * @param alarmIdentifier アラーム識別子
     */
    public SystemException(final String message, final Throwable cause, final AlarmIdentifier alarmIdentifier) {
        super(message, cause, alarmIdentifier);
    }
}
