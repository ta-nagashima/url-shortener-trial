package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidChargeApplicationEnd implements ChargeApplicationEnd{
    private final ChargeApplicationEndDateTime chargeApplicationEndDateTime;

    @Override
    public String getChargeApplicationEndDateTimeForApiValue() {
        return chargeApplicationEndDateTime.getApiValue();
    }
}