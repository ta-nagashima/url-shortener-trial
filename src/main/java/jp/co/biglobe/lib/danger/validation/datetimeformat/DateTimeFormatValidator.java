package jp.co.biglobe.lib.danger.validation.datetimeformat;


import jp.co.biglobe.lib.publication.validation.DateTimeFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateTimeFormatValidator implements ConstraintValidator<DateTimeFormat, String> {

    private static final String DATE_TIME_FORMAT = "uuuuMMddHHmmss";

    @Override
    public void initialize(DateTimeFormat required) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null || "".equals(value)){
            return true;
        }

        if (value.length() != DATE_TIME_FORMAT.length()) {
            return false;
        }

        try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}