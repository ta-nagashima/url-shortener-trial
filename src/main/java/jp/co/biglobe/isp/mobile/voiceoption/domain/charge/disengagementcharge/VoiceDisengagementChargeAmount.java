package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 解約手数料（税込み）
 */

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceDisengagementChargeAmount {

    private final Integer value;

    public String getStringValue(){
        return String.valueOf(value);
    }
}
