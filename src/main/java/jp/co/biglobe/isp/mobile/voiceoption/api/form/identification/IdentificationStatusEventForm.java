package jp.co.biglobe.isp.mobile.voiceoption.api.form.identification;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
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
public class IdentificationStatusEventForm implements FormToValueObject<IdentificationStatusEvent> {

    @Getter
    @NotBlank
    @EnumForApiValue(IdentificationStatusEvent.class)
    @Pattern(regexp = "ok|ng")
    private String value;

    @Override
    public IdentificationStatusEvent getValueObject() {
        return EnumApiValueConverter.convert(IdentificationStatusEvent.class, value);
    }
}
