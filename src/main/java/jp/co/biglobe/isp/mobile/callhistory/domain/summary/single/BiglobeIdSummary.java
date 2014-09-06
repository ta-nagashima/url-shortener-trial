package jp.co.biglobe.isp.mobile.callhistory.domain.summary.single;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.ChargeItem;
import jp.co.biglobe.isp.mobile.callhistory.service.SummaryReferService;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

/**
 * chargeItem、lteThreeGEngagementNumberで一意になる。
 */


@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class BiglobeIdSummary {

    private final ChargeItem chargeItem;

    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    private final Optional<SumChargeAmount> sumChargeAmount;

    public BiglobeIdSummary(ChargeItem chargeItem, LteThreeGEngagementNumber lteThreeGEngagementNumber, SumChargeAmount sumChargeAmount) {
        this.chargeItem = chargeItem;
        this.lteThreeGEngagementNumber = lteThreeGEngagementNumber;

        if (sumChargeAmount == null) {
            this.sumChargeAmount = Optional.empty();
        } else {
            this.sumChargeAmount = Optional.of(sumChargeAmount);
        }

    }

    public String getChargeNameApiValue() {
        return chargeItem.getChargeName().getApiValue();
    }

    public String getLteThreeGEngagementNumberApiValue() {
        return lteThreeGEngagementNumber.getApiValue();
    }

    public String getSumChargeAmountApiValue() {
        return sumChargeAmount.map(s -> s.getApiValue()).orElse("-");
    }

    public String getUnitPriceApiValue() {
        return chargeItem.getUnitPriceOptional().map(s -> s.getApiValue()).orElse("-");
    }

    public boolean isExistChargeAmount() { return this.sumChargeAmount.isPresent(); }

    public boolean isCallKind(CallKind callKind) {
        return chargeItem.isCallKind(callKind);
    }

}
