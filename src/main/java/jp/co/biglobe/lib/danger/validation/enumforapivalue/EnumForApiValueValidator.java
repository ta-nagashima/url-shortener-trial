package jp.co.biglobe.lib.danger.validation.enumforapivalue;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.validation.EnumForApiValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumForApiValueValidator implements ConstraintValidator<EnumForApiValue, String> {

    private Enum[] enumValues;

    @Override
    public void initialize(EnumForApiValue required) {

        Class<? extends Enum<?>> enumClass = required.value();
        enumValues = enumClass.getEnumConstants();
        verifyEnum();

    }

    private void verifyEnum(){

        if(enumValues[0] instanceof EnumConvertForApi){
            return;
        }

        throw new RuntimeException("Enum用のFormクラスを作成する場合は、対象のEnumクラスにEnumConvertForApiを継承する必要があります。");

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || "".equals(value)) {
            return true;
        }

        for(Enum enumValue : enumValues){
            EnumConvertForApi enumConvertForApi = (EnumConvertForApi)enumValue;
            if(enumConvertForApi.getApiValue().equals(value)){
                return true;
            }
        }

        return false;

    }

}