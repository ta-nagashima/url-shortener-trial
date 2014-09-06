package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail;

import org.joda.time.DateTime;

import java.util.Date;

public class VoiceSendMailDate {
    private final Date value;

    /**
     * コンストラクタ
     */
    public VoiceSendMailDate(final Date value){
        this.value = new Date(value.getTime());
    }

    /**
     *  日付を"yyyyMMdd"の形式に整形する
     *
     *  @return 整形済の日時
     */
    public String getDay() {
        return format("yyyyMMdd", value);
    }

    /**
     *  日付を"yyyyMMddHHmmss"の形式に整形する
     *
     *  @return 整形済の日時
     */
    public String getDatetime(){
        return format("yyyyMMddHHmmss", value);
    }

    /**
     *  日付を"yyyy/MM/dd  HH:mm"の形式に整形する
     *
     *  @return 整形済の日時
     */
    public String getLegible(){
        return format("yyyy/MM/dd  HH:mm", value);
    }

    private String format(final String format, final Date target){
        DateTime dateTime = new DateTime(target.getTime());
        return dateTime.toString(format);
    }
}
