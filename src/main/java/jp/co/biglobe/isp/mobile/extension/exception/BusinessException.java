package jp.co.biglobe.isp.mobile.extension.exception;

import jp.co.biglobe.lib.publication.alarmidentifier.AlarmIdentifier;
import jp.co.biglobe.lib.publication.exception.errorstatus.ErrorStatus;
import jp.co.biglobe.lib.publication.exception.errorstatus.HasErrorStatus;
import jp.co.biglobe.lib.publication.exception.statuscode.HasStatusCode;
import jp.co.biglobe.lib.publication.exception.type.NoTransferException;

/**
 * ビジネス例外
 *
 * エラーメールによる通知は行わない
 */
public class BusinessException extends NoTransferException implements HasErrorStatus, HasStatusCode {
    /**
     * エラーステータス
     */
    private ErrorStatus errorStatus;

    /**
     * コンストラクタ
     *
     * アラーム識別子の指定あり
     */
    public BusinessException(final String message, final ErrorStatus errorStatus, final AlarmIdentifier alarmIdentifier) {
        super(message, alarmIdentifier);
        this.errorStatus = errorStatus;
    }

    /**
     * ビジネスエラーステータスを取得
     *
     * @return エラーステータス
     */
    @Override
    public ErrorStatus getErrorStatus(){
        return errorStatus;
    }

    @Override
    public String getStatusCode() {
        return STATUS_CODE;
    }
    private static final String STATUS_CODE = "business_error";
}
