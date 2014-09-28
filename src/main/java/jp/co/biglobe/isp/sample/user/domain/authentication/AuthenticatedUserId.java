package jp.co.biglobe.isp.sample.user.domain.authentication;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public final class AuthenticatedUserId {
    @Getter
    private final String value;
}
