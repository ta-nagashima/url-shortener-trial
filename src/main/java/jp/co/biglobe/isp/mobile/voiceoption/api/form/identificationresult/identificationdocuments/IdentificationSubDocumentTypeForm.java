package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.identificationdocuments;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class IdentificationSubDocumentTypeForm implements FormToValueObject<IdentificationSubDocumentType> {

    @Getter
    @NotBlank
    @EnumForApiValue(IdentificationSubDocumentType.class)
    private String value;

    @Override
    public IdentificationSubDocumentType getValueObject() {
        return EnumApiValueConverter.convert(IdentificationSubDocumentType.class, value);
    }
}
