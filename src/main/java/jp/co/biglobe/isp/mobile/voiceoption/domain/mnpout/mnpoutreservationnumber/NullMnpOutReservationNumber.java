package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
public class NullMnpOutReservationNumber implements MnpOutReservationNumber {


    @Override
    public String getMnpReservationNumberForApiValue(){
        return "";
    }

    @Override
    public String getExpireDateForApiValue(){
        return "";
    }

    @Override
    public String getExecutionDateForApiValue(){
        return "";
    }

    @Override
    public boolean hasNumber() {
        return false;
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() {
        return !(isExist());
    }

    @Override
    public boolean isValidReservationNumber() {
        return false;
    }

}
