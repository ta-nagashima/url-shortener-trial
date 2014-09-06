package jp.co.biglobe.isp.mobile.voiceoption.domain.view.voiceengagementcountrefer;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementCount implements ValueObjectConvertForApi {
    private final Integer value;

    @Override
    public String getApiValue(){
        return String.valueOf(value);
    }

    public boolean isNotExist(){
        if (value == 0){ return true;}

        return false;
    }
}
