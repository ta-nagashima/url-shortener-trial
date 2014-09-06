package jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement;

import jp.co.biglobe.lib.publication.form.FormToValueObject;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGServicePlanCodeForm implements FormToValueObject<LteThreeGServicePlanCode> {

    @Getter
    @NotBlank
    @EnumForApiValue(LteThreeGServicePlanCode.class)
    private String value;

    @Override
    public LteThreeGServicePlanCode getValueObject(){return EnumApiValueConverter.convert(LteThreeGServicePlanCode.class, value);}

}
