package jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid.operatorauthbysessionIdinput;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperatorAuthBySessionIdNoSsoInput implements OperatorAuthBySessionIdInput {

    // 認証方式
    @Mapping("auth_method_kbn")
    private final String authMethodKbn = AuthenticationMethod.OPERATION_AUTHORITY_AUTHENTICATION_WITHOUT_BIGLOBE_ID_SPECIFICATION.getMethod();

    // 担当ID
    @Mapping("tantoid")
    private final OperatorId operatorId;

    // 加入ルート
    @Mapping("kanyu_rout")
    private final String kanyuRout = "BCS";

    // 操作者ISP/ASP識別子
    @Mapping("sosa_sp_id")
    private final String sosaSpId = "BIGLOBE";

    // 操作権限認証SSOフラグ
    @Mapping("tanto_sso_flg")
    private final String tanto_sso_flg = "0";

    // セッション種別
    @Mapping("s_type")
    private final String sType = "006";

    // セッションID
    @Mapping("sid")
    private final SessionId sessionID;

    // パスワードの有効期限チェックの有無
    @Mapping("op_auth_type")
    private final String opAuthType = "1000";

}
