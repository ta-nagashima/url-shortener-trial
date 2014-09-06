package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge;

import jp.co.biglobe.isp.mobile.extension.domain.TaxCalculator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.text.NumberFormat;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class SpeedChargeFee {
    private final Integer value;

    public String getValueWithTaxSeparateComma(){
        int valueWithTax = TaxCalculator.getTruncationPoint(value);
        return NumberFormat.getNumberInstance().format(valueWithTax);
    }

    public String getValueWithOutTaxSeparateComma(){
        return NumberFormat.getNumberInstance().format(value);
    }


}
