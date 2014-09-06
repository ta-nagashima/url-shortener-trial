package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class ChargeItem {

    @Getter
    private final ChargeId chargeId;

    @Getter
    private final ChargeName chargeName;

    @Getter
    private final TaxStatus taxStatus;

    @Getter
    private final CallKind callKind;

    @Getter
    private final CommunicationMethod communicationMethod;

    @Getter
    private final Optional<UnitPrice> unitPriceOptional;

    // Constructor
    public ChargeItem(
            ChargeId chargeId, ChargeName chargeName, TaxStatus taxStatus, CallKind callKind,
                      CommunicationMethod communicationMethod, UnitPrice unitPrice){

        this.chargeId = chargeId;
        this.chargeName = chargeName;
        this.taxStatus = taxStatus;
        this.callKind = callKind;
        this.communicationMethod = communicationMethod;
        if(unitPrice == null){
            this.unitPriceOptional = Optional.empty();
        }else{
            this.unitPriceOptional = Optional.of(unitPrice);
        }

    }

    public boolean isCallKind(CallKind callKind){
        return this.callKind == callKind;
    }

}
