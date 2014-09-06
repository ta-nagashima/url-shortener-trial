package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidSpeedChargeApplicationEnd implements SpeedChargeApplicationEnd{

    private final SpeedChargeApplicationEndDateTime speedChargeApplicationEndDateTime;

    @Override
    public String getSpeedChargeApplicationEndDateTimeForApiValue() {
        return speedChargeApplicationEndDateTime.getApiValue();
    }

}
