package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.UploadLimitTerm;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceUserOrderDate implements ValueObjectConvertForApi {
    private final String value;

    public String getApiValue() {
        return value;
    }

    /**
     * もし、MnpInがあるときにアップロード期日内かどうかを返却
     */
    boolean isIdentificationUploadDeadlineOverForMnpIn() {
        DateTime nowDateTime = new DateTime();
        DateTime limitDateTime = getLimitDateTime();

        return isDeadlineOver(nowDateTime, limitDateTime);
    }

    /**
     * 本人確認未実施の場合のオーダーの有効期限内かどうかを返却
     */
    boolean isVoiceEngagementOrderDeadlineOver() {
        DateTime nowDateTime = new DateTime();
        DateTime limitDateTime = getVoiceEngagementOrderValidDateTime();

        return isDeadlineOver(nowDateTime, limitDateTime);
    }


    private boolean isDeadlineOver(DateTime nowDateTime, DateTime limitDateTime) {
        if (nowDateTime.isAfter(limitDateTime)) {
            return true;
        }
        return false;
    }

    /**
     * アップロードできる期日を取得
     */
    private DateTime getLimitDateTime() {

        DateTime orderDateTime = getValueWithDateTime();
        return orderDateTime.plusDays(UploadLimitTerm.CAN_UPLOAD_AFTER_DAY).withTime(0, 0, 0, 0);
    }

    /**
     * もし、MnpInがあるときの予約番号の有効期限の最終日を取得
     */
    private DateTime getVoiceEngagementOrderValidDateTime() {
        DateTime orderDateTime = getValueWithDateTime();
        return orderDateTime.plusDays(VoiceEngagementOrderValidityTerm.VALIDITY_TERM - 1).withTime(0, 0, 0, 0);
    }

    private DateTime getValueWithDateTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(value);

            return new DateTime(date);

        } catch (ParseException e) {
            throw new SystemCheckException("音声オプション契約申込日フォーマットエラー", VoiceOptionAlarmIdentifier.DEFAULT);
        }
    }
}
