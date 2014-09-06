package jp.co.biglobe.isp.auth.domain.operatorwithuser;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorAuth;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * BIGLOBEオペレーター
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class OperatorWithUserAuth implements OperatorAuth {

    @Getter
    private final ValidOperatorNoUserAuth validOperatorNoUserAuth;

    @Getter
    private final SimpleUserAuth simpleUserAuth;
}
