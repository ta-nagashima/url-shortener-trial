package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistMnpOutPersonalInfo implements MnpOutPersonalInfo {

    @Override
    public String getMnpFullNameForApiValue(){
        return "";
    }

    @Override
    public String getMnpFullNameKanaForApiValue(){
        return "";
    }

    @Override
    public String getMnpGenderForApiValue(){
        return "";
    }

    @Override
    public String getMnpBirthdayForApiValue(){
        return "";
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() {
        return !(isExist());
    }
}
