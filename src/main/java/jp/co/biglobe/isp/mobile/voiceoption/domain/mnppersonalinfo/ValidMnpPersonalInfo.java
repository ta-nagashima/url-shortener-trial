package jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpPersonalInfo implements MnpPersonalInfo {

    @Getter
    private final MnpFullName mnpFullName;

    @Getter
    private final MnpFullNameKana mnpFullNameKana;

    @Getter
    private final MnpGender mnpGender;

    @Getter
    private final MnpBirthday mnpBirthday;


    @Override
    public String getMnpFullNameForApiValue(){
        return mnpFullName.getApiValue();
    }

    @Override
    public String getMnpFullNameKanaForApiValue(){
        return mnpFullNameKana.getApiValue();
    }

    @Override
    public String getMnpGenderForApiValue(){
        return mnpGender.getApiValue();
    }

    @Override
    public String getMnpBirthdayForApiValue(){
        return mnpBirthday.getApiValue();
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() {
        return !(isExist());
    }
}
