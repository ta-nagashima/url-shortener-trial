package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.ChargeItem;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CommunicationMethod;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class History {

    private final HistoryId historyId;

    private final ValidMsisdn validMsisdn;

    private final ChargeItem chargeItem;

    private final StartDateTime startDateTime;

    private final ChargeAmount chargeAmount;

    private final ConnectCountry connectCountry;

    private final Optional<CallDuration> callDurationOptional;

    // Constructor
    public History(HistoryId historyId, ValidMsisdn validMsisdn, ChargeItem chargeItem, StartDateTime startDateTime,
                   ChargeAmount chargeAmount, ConnectCountry connectCountry, CallDuration callDuration) {
        this.historyId = historyId;
        this.validMsisdn = validMsisdn;
        this.chargeItem = chargeItem;
        this.startDateTime = startDateTime;
        this.chargeAmount = chargeAmount;
        this.connectCountry = connectCountry;
        if(callDuration == null){
            this.callDurationOptional = Optional.empty();
        }else{
            this.callDurationOptional = Optional.of(callDuration);
        }
    }

    public String getTaxStatusApiValue(){
        return chargeItem.getTaxStatus().getApiValue();
    }

    public String getStartDateTimeApiValue(){
        return startDateTime.getApiValue();
    }

    public String getDialedMsisdnApiValue(){
        return validMsisdn.getValue();
    }

    public String getCommunicationMethodApiValue(){
        return chargeItem.getCommunicationMethod().getApiValue();
    }

    public String getChargeItemNameApiValue(){
        return chargeItem.getChargeName().getApiValue();
    }

    public String getDurationApiValue(){

        String str = callDurationOptional.map( s -> s.getApiValue()).orElse("00:00:00");

        if(chargeItem.getCommunicationMethod() == CommunicationMethod.SMS){
            return "-";
        }

        return str;
    }

    public String getChargeAmountApiValue(){
        return chargeAmount.getApiValue();
    }

    public String getConnectCountryForApiValue(){
        return connectCountry.getApiValue();
    }

}
