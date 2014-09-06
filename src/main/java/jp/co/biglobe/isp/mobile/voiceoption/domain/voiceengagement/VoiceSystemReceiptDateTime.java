package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceSystemReceiptDateTime implements ValueObjectConvertForApi{
    @Getter
    private final Date value;

    public VoiceSystemReceiptDateTime(){
        this.value = new SystemDateTime().getValue();
    }

    @Override
    public String getApiValue(){
        return DateToString.get_yyyyMMddHHmmss(value);
    }
}
