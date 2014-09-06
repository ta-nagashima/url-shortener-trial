package jp.co.biglobe.isp.mobile.callhistory.domain.summary.single;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

/**
 * msisdn、callKindで一意になる。
 */

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class MsisdnSummary {

    @Getter
    private final ValidMsisdn validMsisdn;

    @Getter
    private final CallKind callKind;

    private final Optional<SumChargeAmount> sumChargeAmountOptional;

    private final Optional<SumCountCallAndTrans> sumCountTransOptional;

    // Constructor
    public MsisdnSummary(
            ValidMsisdn validMsisdn, CallKind callKind,
            SumChargeAmount sumChargeAmount, SumCountCallAndTrans sumCountCallAndTrans) {
        this.validMsisdn = validMsisdn;
        this.callKind = callKind;
        if (sumChargeAmount == null) {
            this.sumChargeAmountOptional = Optional.empty();
        } else {
            this.sumChargeAmountOptional = Optional.of(sumChargeAmount);
        }
        if (sumCountCallAndTrans == null) {
            this.sumCountTransOptional = Optional.empty();
        } else {
            this.sumCountTransOptional = Optional.of(sumCountCallAndTrans);
        }

    }


    public boolean isCallKind(CallKind callKind) {
        return this.callKind.equals(callKind);
    }


    public String getMsisdnApiValue() {
        return validMsisdn.getValue();
    }

    public String getSumChargeAmountApiValue() {

        return sumChargeAmountOptional.map(s -> s.getApiValue()).orElse("-");

    }

    public boolean isExistCountTransOptional() {
        return this.sumCountTransOptional.isPresent();
    }

    public String getSumCountTransApiValueForDetail() {

        return sumCountTransOptional.map(s -> s.getApiValue()).orElse("0");
    }


}
