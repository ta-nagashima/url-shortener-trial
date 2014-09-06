package jp.co.biglobe.isp.auth.domain.operatornouser;

import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum OperatorAuthMethodType {
    SESSION_ID_SSO,
    SESSION_ID_NO_SSO,
    ID_PASSWORD
}
