package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.isp.auth.domain.user.FullName;
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
public class ValidOperatorNoUserAuth implements OperatorAuth {
    @Getter
    private final OperatorId operatorId;
    @Getter
    private final FullName fullName;
}
