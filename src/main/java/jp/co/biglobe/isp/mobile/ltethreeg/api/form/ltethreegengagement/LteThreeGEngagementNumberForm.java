package jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
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
public class LteThreeGEngagementNumberForm implements FormToValueObject<LteThreeGEngagementNumber> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[0-9]{8}$")
    private String value;

    @Override
    public LteThreeGEngagementNumber getValueObject(){
        return new LteThreeGEngagementNumber(value);
    }

}
