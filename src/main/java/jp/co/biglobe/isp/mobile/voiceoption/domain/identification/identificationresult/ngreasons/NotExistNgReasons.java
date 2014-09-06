package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistNgReasons implements NgReasons {

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public String getNgReasonForApiValue() {return "";}

    @Override
    public String getNgReasonDetailForApiValue() {return "";}
}
