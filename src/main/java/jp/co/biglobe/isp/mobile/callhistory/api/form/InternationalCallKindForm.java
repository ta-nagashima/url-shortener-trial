package jp.co.biglobe.isp.mobile.callhistory.api.form;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
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
public class InternationalCallKindForm implements FormToValueObject<CallKind> {

    @Getter
    @NotBlank
    @Pattern(regexp = "^[1-2]$")
    private String value;

    @Override
    public CallKind getValueObject() {
        return EnumApiValueConverter.convert(CallKind.class, value);
    }
}
