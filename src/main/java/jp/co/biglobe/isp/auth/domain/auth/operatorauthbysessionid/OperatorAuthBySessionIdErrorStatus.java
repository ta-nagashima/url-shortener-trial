package jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid;

import jp.co.biglobe.isp.auth.domain.exception.authentication.AuthenticationErrorStatus;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum OperatorAuthBySessionIdErrorStatus implements AuthenticationErrorStatus, EnumConvertForApi {

    INVALID_ID_PW("ID/PWが不正エラー",OperatorAuthBySessionIdErrorTypeApiValue.INVALID_ID_PW.INVALID_ID_PW),
    BEFORE_SERVICE("サービス開始前エラー",OperatorAuthBySessionIdErrorTypeApiValue.BEFORE_SERVICE),
    END_SERVICE("サービス終了日後エラー",OperatorAuthBySessionIdErrorTypeApiValue.END_SERVICE),
    SESSION_TIMEOUT("セッション切れエラー",OperatorAuthBySessionIdErrorTypeApiValue.SESSION_TIMEOUT),
    SYSTEM_ERROR("システムエラー",OperatorAuthBySessionIdErrorTypeApiValue.SYSTEM_ERROR);

    @Getter
    private final String message;

    private final OperatorAuthBySessionIdErrorTypeApiValue operatorAuthBySessionIdErrorTypeApiValue;

    @Override
    public String getStatus(){
        return getApiValue();
    }

    @Override
    public String getApiValue(){
        return operatorAuthBySessionIdErrorTypeApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum OperatorAuthBySessionIdErrorTypeApiValue implements ApiValue {
        INVALID_ID_PW("invalid_id_pw" /* 文字列リテラルの変更禁止 */),
        BEFORE_SERVICE("before_service" /* 文字列リテラルの変更禁止 */),
        END_SERVICE("end_service" /* 文字列リテラルの変更禁止 */),
        SESSION_TIMEOUT("session_timeout" /* 文字列リテラルの変更禁止 */),
        SYSTEM_ERROR("system_error" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
