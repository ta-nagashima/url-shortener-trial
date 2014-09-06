package jp.co.biglobe.isp.auth.api.request;

import jp.co.biglobe.isp.auth.api.form.SessionIdForm;
import jp.co.biglobe.isp.auth.domain.user.UserAuthManagement;
import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@ToString(includeFieldNames = false)
public class UserAuthRequest {

    @Deprecated
    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private SessionIdForm sessionIdForm;


    public UserAuthManagement getUserAuthManagement() {
        return new UserAuthManagement(sessionIdForm.getValueObject());
    }


}
