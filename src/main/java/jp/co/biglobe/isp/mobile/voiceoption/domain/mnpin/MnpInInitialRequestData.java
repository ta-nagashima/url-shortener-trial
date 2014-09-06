package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;


import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpPersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class MnpInInitialRequestData {

    @Getter
    private VoiceMsisdn voiceMsisdn;

    @Getter
    private MnpReservationNumber mnpReservationNumber;

    @Getter
    private MnpPersonalInfo mnpInPersonalInfo;

}
