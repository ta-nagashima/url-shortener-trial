package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames=false)
public class TotalAuthRequest {

    @Getter
    @Setter
    @Valid
    private AuthenticationMethodForm authenticationMethodForm;

    @Getter
    @Setter
    @Valid
    private TantoSsoFlagForm tantoSsoFlagForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;

    @Getter
    @Setter
    @Valid
    private OperatorIdForm operatorIdForm;

    @Getter
    @Setter
    @Valid
    private OptionalUserIdForm userIdForm;

    public TotalAuth getTotalAuthForm(){

        return TotalAuthRequestFactory.create(this);
    }

}
