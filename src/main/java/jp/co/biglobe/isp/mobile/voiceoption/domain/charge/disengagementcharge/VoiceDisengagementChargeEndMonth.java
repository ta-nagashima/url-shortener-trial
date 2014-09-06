package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * 解約手数料が終わる（＝この月の翌月にやめる場合から０円）の月
 */

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceDisengagementChargeEndMonth {

    @Getter
    private final String value;

    private static final String DATE_FORMAT = "yyyyMM";

    boolean isAfterNowMonth(){

        return convertToDateTime().isAfter(now());
    }

    boolean isBeforeNowMonth(){

        return convertToDateTime().isBefore(now());
    }

    boolean isEqualNowMonth(){

        return convertToDateTime().isEqual(now());
    }


    // valueをDateTimeに変更
    private DateTime convertToDateTime(){
        return DateTimeFormat.forPattern(DATE_FORMAT).parseDateTime(value);
    }

    // 現在の月の初日を取得
    private DateTime now(){
        return new DateTime().monthOfYear().roundFloorCopy();
    }

}
