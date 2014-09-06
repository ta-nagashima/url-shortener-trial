package jp.co.biglobe.isp.auth.domain.exception.authentication;

import jp.co.biglobe.lib.publication.exception.errorstatus.ErrorStatus;
import jp.co.biglobe.lib.publication.exception.errorstatus.HasErrorStatus;
import jp.co.biglobe.lib.publication.exception.statuscode.HasStatusCode;
import jp.co.biglobe.lib.publication.exception.type.NoTransferException;

/**
 * 認証エラー時に発生する例外
 */
public class AuthenticationException extends NoTransferException implements HasErrorStatus, HasStatusCode {
    /**
     * エラーステータス
     */
    private ErrorStatus errorStatus;

    /**
     * コンストラクタ
     *
     * @param errorStatus 認証エラーステータス
     */
    public AuthenticationException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    /**
     * エラーステータスを取得
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
    private static final String STATUS_CODE = "authentication_error";
}
