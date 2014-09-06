package jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
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
public class MsisdnForm implements FormToValueObject<ValidMsisdn> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$")
    private String value;


    @Override
    public ValidMsisdn getValueObject() {
        return new ValidMsisdn(value);
    }
}
