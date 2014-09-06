package jp.co.biglobe.isp.mobile.extension.date;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {

    public static String get_yyyyMMddHHmmss(Date date){

        final String DATE_PATTERN ="yyyyMMddHHmmss";
        return (new SimpleDateFormat(DATE_PATTERN)).format(date);

    }
    public static String get_yyyyMM(Date date){

        final String DATE_PATTERN ="yyyyMM";
        return (new SimpleDateFormat(DATE_PATTERN)).format(date);

    }
}
