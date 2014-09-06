package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperatorAuthWithBiglobeIdInput {

    // 認証方式
    @Mapping("auth_method_kbn")
    private final String authMethodKbn = AuthenticationMethod.OPERATION_AUTHORITY_AUTHENTICATION_BIGLOBE_ID_SPECIFICATION.getMethod();

    // 担当ID
    @Mapping("tantoid")
    private final OperatorId operatorId;

    // 担当パスワード（bv92kwkcの１６進数表記）
    @Mapping("tantopwd")
    private final OperatorPassword operatorPassword;

    // 加入ルート
    @Mapping("kanyu_rout")
    private final String kanyuRout = "BCS";

    // 操作者ISP/ASP識別子
    @Mapping("sosa_sp_id")
    private final String sosaSpId = "BIGLOBE";

    // セッション種別
    @Mapping("s_type")
    private final String sType = "006";

    // 被操作者ID
    @Mapping("hi_sosaid")
    private  final UserId userId;

    // パスワードの有効期限チェックの有無
    @Mapping("op_auth_type")
    private final String opAuthType = "1000";

    // 認証チェックレベル：本人認証固定
    // 認証チェックは何もチェックせず、ドメイン側でチェックする
    @Mapping("idpass_auth_type")
    private final String idpassAuthType = "899999999";


}
