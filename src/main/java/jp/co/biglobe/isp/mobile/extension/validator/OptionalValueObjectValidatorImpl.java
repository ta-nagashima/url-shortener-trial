package jp.co.biglobe.isp.mobile.extension.validator;


import jp.co.biglobe.isp.mobile.extension.validator.OptionalValueObjectValidator;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class OptionalValueObjectValidatorImpl implements OptionalValueObjectValidator {

    private static final String ADD_FIELD_NAME = ".value";

    @Override
    public void run(String fieldName, Errors errors){

        rejectIfEmptyOrWhitespace(fieldName ,errors);
        if(errors.hasFieldErrors(fieldName)){
            return;
        }

        rejectIfEmptyOrWhitespace(fieldName + ADD_FIELD_NAME, errors);

    }

    private void rejectIfEmptyOrWhitespace(String fieldName, Errors errors){

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName,
                "NotEmpty.registration." + fieldName,
                fieldName + " must not be Empty.");

    }

    @Override
    public boolean hasErrors(String fieldName, Errors errors){

        if(errors.hasFieldErrors(fieldName)){
            return true;
        }

        if(errors.hasFieldErrors(fieldName + ADD_FIELD_NAME)){
            return true;
        }

        return false;
    }

    @Override
    public <ENUM extends Enum<ENUM> & EnumConvertForApi> void rejectIfEnumNoApiValue
            (Class<ENUM> enumClass, String formValue, Errors errors, String fieldName){

        ENUM[] enumValues = enumClass.getEnumConstants();
        for(ENUM enumValue : enumValues){
            if(enumValue.getApiValue().equals(formValue)){
                return;
            }
        }

        errors.rejectValue(fieldName + ADD_FIELD_NAME,
                "notMatching.registration." + fieldName + ADD_FIELD_NAME,
                null, fieldName + ADD_FIELD_NAME + " Not match.");
    }
}
