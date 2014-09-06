package jp.co.biglobe.isp.auth.domain.operatornouser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * シナリオで認証なしのときに使うデフォルトのId
 */

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class DefaultOperatorId {

    @Getter
    private final String value = "SYSTEM";
}
