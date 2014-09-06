package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.end;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NoExistChargeApplicationEnd implements ChargeApplicationEnd{
    @Override
    public String getChargeApplicationEndDateTimeForApiValue() {
        return "";
    }
}