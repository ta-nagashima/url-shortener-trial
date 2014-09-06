package jp.co.biglobe.isp.mobile.biglobedenwa.api.form;


import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthday;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeDenwaBirthdayForm implements FormToValueObject<BiglobeDenwaBirthday> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{4}$")
    private String value;

    @Override
    public BiglobeDenwaBirthday getValueObject() {
        return new BiglobeDenwaBirthday(value);
    }


}
