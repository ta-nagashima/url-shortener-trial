package jp.co.biglobe.isp.mobile.voiceoption.api.form.identificationresult.ngreasons;

import jp.co.biglobe.lib.publication.validation.EnumForApiValue;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReason;
import jp.co.biglobe.lib.publication.form.EnumApiValueConverter;
import jp.co.biglobe.lib.publication.form.FormToValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NgReasonForm implements FormToValueObject<NgReason> {


    @Getter
    @EnumForApiValue(NgReason.class)
    private String value;

    @Override
    public NgReason getValueObject(){

        /**
         * NgReasonFactoryのヌルポ対策のために、
         * nullチェックを入れている。
         */

        if(value == ""){
            return null;
        }

        return EnumApiValueConverter.convert(NgReason.class, value);
    }
}
