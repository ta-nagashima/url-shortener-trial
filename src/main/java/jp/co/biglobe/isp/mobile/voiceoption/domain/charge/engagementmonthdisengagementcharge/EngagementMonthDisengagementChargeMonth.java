package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementEndDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * 契約月解約課金が行われる月（＝この翌月にやめる場合から０円）
 */

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class EngagementMonthDisengagementChargeMonth {

    @Getter
    private final String value;

    private static final String DATE_FORMAT = "yyyyMM";

    boolean isEqualMonth(VoiceEngagementEndDateTime voiceEngagementEndDateTime) {
        return convertToDateTime().getMonthOfYear() == voiceEngagementEndDateTime.getMonth();
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
