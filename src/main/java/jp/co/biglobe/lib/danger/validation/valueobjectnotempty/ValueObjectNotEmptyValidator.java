package jp.co.biglobe.lib.danger.validation.valueobjectnotempty;


import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueObjectNotEmptyValidator implements ConstraintValidator<ValueObjectNotEmpty, Object> {

    static final String FIELD_NAME = "value";

    @Override
    public void initialize(ValueObjectNotEmpty required) {}

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        final BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        NotPrimitive.verify(wrapper.getPropertyType(FIELD_NAME));
        Validation valueObjectValidation = ValidationFactory.newInstance(wrapper);

        return valueObjectValidation.run();

    }
}