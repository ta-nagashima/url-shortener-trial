package jp.co.biglobe.lib.danger.validation.monthformat;


import jp.co.biglobe.lib.publication.validation.YearMonthFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class YearMonthFormatValidator implements ConstraintValidator<YearMonthFormat, String> {

    private static final String DATE_FORMAT = "uuuuMM";

    @Override
    public void initialize(YearMonthFormat required) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null || "".equals(value)){
            return true;
        }

        if(value.length() != DATE_FORMAT.length()){
            return false;
        }

        try {
            YearMonth.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}