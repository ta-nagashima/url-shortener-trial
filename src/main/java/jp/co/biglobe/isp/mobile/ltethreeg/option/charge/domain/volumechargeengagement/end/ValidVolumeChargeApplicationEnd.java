package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVolumeChargeApplicationEnd implements VolumeChargeApplicationEnd{
    private final VolumeChargeApplicationEndDateTime volumeChargeApplicationEndDateTime;

    @Override
    public String getVolumeChargeApplicationEndDateTimeForApiValue() {
        return volumeChargeApplicationEndDateTime.getApiValue();
    }
}