package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidSpeedChargeApplicationEndFactory{

    public static ValidSpeedChargeApplicationEnd create(){
        return new ValidSpeedChargeApplicationEnd(SpeedChargeApplicationEndDateTime.create());
    }
}
