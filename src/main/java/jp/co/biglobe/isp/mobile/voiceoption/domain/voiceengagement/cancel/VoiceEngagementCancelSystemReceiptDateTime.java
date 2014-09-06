package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementCancelSystemReceiptDateTime {
    @Getter
    private final Date value;
}
