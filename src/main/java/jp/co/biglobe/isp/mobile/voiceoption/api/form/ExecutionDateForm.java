package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.DateFormat;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ExecutionDateForm implements FormToValueObject<ExecutionDate> {

    @Getter
    @NotBlank
    @DateFormat
    private String value;

    @Override
    public ExecutionDate getValueObject(){
        return new ExecutionDate(value);
    }
}

