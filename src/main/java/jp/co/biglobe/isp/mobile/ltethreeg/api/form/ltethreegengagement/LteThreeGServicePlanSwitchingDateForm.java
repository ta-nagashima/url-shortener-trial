package jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanSwitchingDate;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGServicePlanSwitchingDateForm implements FormToValueObject<LteThreeGServicePlanSwitchingDate> {

    @Getter
    @NotBlank
    @DateFormat
    private String value;

    @Override
    public LteThreeGServicePlanSwitchingDate getValueObject(){
        return new LteThreeGServicePlanSwitchingDate(value);
    }

}
