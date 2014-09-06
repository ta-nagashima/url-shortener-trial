package jp.co.biglobe.lib.danger.validation.dateformat;


import jp.co.biglobe.lib.publication.validation.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    private static final String DATE_FORMAT = "uuuuMMdd";

    @Override
    public void initialize(DateFormat required) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == null || "".equals(value)){
            return true;
        }

        if(value.length() != DATE_FORMAT.length()){
            return false;
        }

        try {
            LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);
//        ParsePosition position = new ParsePosition(0);
//        TemporalAccessor temporal = formatter.parseUnresolved(value, position);
//
//        if(temporal == null || position.getErrorIndex() == -1){
//            return false;
//        }
//        return true;

    }

}