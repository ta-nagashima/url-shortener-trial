package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import jp.co.biglobe.lib.publication.scenario.authentication.AuthenticationMethod;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class AuthenticationMethodForm implements FormToValueObject<AuthenticationMethod> {

    @Getter
    private String value;

    @Override
    public AuthenticationMethod getValueObject() {
        return EnumApiValueConverter.convert(AuthenticationMethod.class, value);
    }

}
