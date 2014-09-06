package jp.co.biglobe.lib.danger.validation.valueobjectnotempty;

import org.springframework.beans.BeanWrapper;

class ValidationFactory {

    private ValidationFactory(){}

    static Validation newInstance(BeanWrapper wrapper) {

        Class propertyType = wrapper.getPropertyType(ValueObjectNotEmptyValidator.FIELD_NAME);
        String className = propertyType.getName();
        Object value = wrapper.getPropertyValue(ValueObjectNotEmptyValidator.FIELD_NAME);
        if (className.equals("java.lang.String")) {
            return new StringValidation(value);
        }

        return new NotStringValidation(value);
    }

}
