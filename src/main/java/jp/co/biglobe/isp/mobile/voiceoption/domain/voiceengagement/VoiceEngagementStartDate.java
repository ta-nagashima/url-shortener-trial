package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementStartDate implements ValueObjectConvertForApi{
    private final String value;

    private static final String DATE_FORMAT = "yyyyMMdd";

    public String getApiValue() {
        return value;
    }

    public Date getDate() {
        return DateTimeFormat.forPattern(DATE_FORMAT).parseDateTime(value).toDate();
    }
}
