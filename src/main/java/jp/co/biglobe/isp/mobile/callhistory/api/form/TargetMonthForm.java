package jp.co.biglobe.isp.mobile.callhistory.api.form;

import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import jp.co.biglobe.lib.publication.validation.YearMonthFormat;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class TargetMonthForm implements FormToValueObject<TargetMonth> {

    @Getter
    @NotBlank
    @YearMonthFormat
    private String value;

    @Override
    public TargetMonth getValueObject(){
        return new TargetMonth(value);
    }
}
