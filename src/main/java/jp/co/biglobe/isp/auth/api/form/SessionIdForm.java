package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.user.SessionId;
import jp.co.biglobe.isp.auth.domain.user.UserAuthManagement;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SessionIdForm  implements FormToValueObject<SessionId> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String value;

    @Override
    public SessionId getValueObject() {
        return new SessionId(value);
    }

    public UserAuthManagement getUserAuthManagement() {
        return new UserAuthManagement(new SessionId(value));
    }

}
