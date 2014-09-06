package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementDisengageSystemReceiptDateTime {
    private final Date value;
}
