package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber;


import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;

public interface MnpOutReservationNumber extends CommonEntity {

    public String getMnpReservationNumberForApiValue();

    public String getExpireDateForApiValue();

    public String getExecutionDateForApiValue();

    public boolean hasNumber();

    public boolean isNotExist();

    public boolean isValidReservationNumber();

}
