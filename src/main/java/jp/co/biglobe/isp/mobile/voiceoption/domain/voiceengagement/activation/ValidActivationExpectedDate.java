package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.activation;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidActivationExpectedDate implements ActivationExpectedDate{
    private final String value;
}
