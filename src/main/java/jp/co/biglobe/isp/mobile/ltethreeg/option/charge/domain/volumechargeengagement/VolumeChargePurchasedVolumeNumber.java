package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VolumeChargePurchasedVolumeNumber {
    @Getter
    private final Integer value;
}
