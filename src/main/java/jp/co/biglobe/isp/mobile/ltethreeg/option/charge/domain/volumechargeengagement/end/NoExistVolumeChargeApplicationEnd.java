package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NoExistVolumeChargeApplicationEnd implements VolumeChargeApplicationEnd{
    @Override
    public String getVolumeChargeApplicationEndDateTimeForApiValue() {
        return "";
    }
}