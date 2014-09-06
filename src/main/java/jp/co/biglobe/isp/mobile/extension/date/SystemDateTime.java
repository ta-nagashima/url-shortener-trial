package jp.co.biglobe.isp.mobile.extension.date;

import org.joda.time.DateTime;

import java.util.Date;

public class SystemDateTime {

    private final Date value;

    /**
     * コンストラクタ
     */
    public SystemDateTime(){
        this.value = new DateTime().toDate();
    }

    public Date getValue(){
        return new Date(value.getTime());
    }


}
