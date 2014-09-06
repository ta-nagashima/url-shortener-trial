package jp.co.biglobe.isp.auth.domain.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * BIGLOBE会員
 */
@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidUserAuthTemp implements UserAuth {
    @Getter
    private final UserId userId;
    @Getter
    private final FullName fullName;
//    @Getter
//    private final ContractType contractType;

}
