package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVolumeChargeApplicationEndFactory {
    public static ValidVolumeChargeApplicationEnd create(){
        return new ValidVolumeChargeApplicationEnd(VolumeChargeApplicationEndDateTime.create());
    }
}