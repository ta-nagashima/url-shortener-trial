package jp.co.biglobe.isp.mobile.voiceoption.api.form;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ReceiptStatus;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ReceiptStatusForm implements FormToValueObject<ReceiptStatus> {

    @Getter
    @NotBlank
    @EnumForApiValue(ReceiptStatus.class)
    private String value;

    @Override
    public ReceiptStatus getValueObject(){
        return EnumApiValueConverter.convert(ReceiptStatus.class, value);
    }
}
