package jp.co.biglobe.lib.publication.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class ForeverDate {

    /**
     * 超未来
     */
    private static final String foreverDate= "29991231235959";

    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse(foreverDate, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

    public String get_yyyyMMdd(){
        return LOCAL_DATE_TIME.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String get_yyyyMMddHHmmss(){
        return LOCAL_DATE_TIME.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public Date getDate(){
        ZonedDateTime zonedDateTime = LOCAL_DATE_TIME.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

}
