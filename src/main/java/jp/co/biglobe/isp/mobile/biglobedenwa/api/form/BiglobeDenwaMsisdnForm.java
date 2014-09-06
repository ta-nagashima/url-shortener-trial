package jp.co.biglobe.isp.mobile.biglobedenwa.api.form;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
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
public class BiglobeDenwaMsisdnForm implements FormToValueObject<BiglobeDenwaMsisdn> {
    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$")
    private String value;

    @Override
    public BiglobeDenwaMsisdn getValueObject(){
        return new BiglobeDenwaMsisdn(value);
    }

}
