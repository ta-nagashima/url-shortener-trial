package jp.co.biglobe.isp.auth.api.form;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class UserIdForm implements FormToValueObject<UserId> {

    @Getter
    @NotBlank
    @Size(min = 7, max = 8)
    @Pattern(regexp = "^[0-9a-zA-Z]+$")
    private String value;

    @Override
    public UserId getValueObject() {
        return new UserId(value);
    }

}
