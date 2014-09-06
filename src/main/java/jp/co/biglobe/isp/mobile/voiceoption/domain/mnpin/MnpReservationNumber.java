package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.extension.valueobject.ValueObjectConvertForApi;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class MnpReservationNumber implements ValueObjectConvertForApi{
    private final String value;

    @Override
    public String getApiValue(){
        return value;
    }
}
