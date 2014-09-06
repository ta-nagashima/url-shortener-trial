package jp.co.biglobe.isp.mobile.extension.validator;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import org.springframework.validation.Errors;

public interface OptionalValueObjectValidator {

    public void run(String fieldName, Errors errors);

    public boolean hasErrors(String fieldName, Errors errors);

    public <ENUM extends Enum<ENUM> & EnumConvertForApi> void rejectIfEnumNoApiValue
            (Class<ENUM> enumClass, String formValue, Errors errors, String fieldName);

}
