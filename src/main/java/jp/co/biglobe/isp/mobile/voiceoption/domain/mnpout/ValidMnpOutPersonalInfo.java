package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpBirthday;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullName;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpFullNameKana;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpGender;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpOutPersonalInfo implements MnpOutPersonalInfo, CommonValidEntity<ValidMnpOutPersonalInfo> {

    @Getter
    private final VoiceMnpOutId voiceMnpOutId;

    private final MnpFullName mnpFullName;

    private final MnpFullNameKana mnpFullNameKana;

    private final MnpGender mnpGender;

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


    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpOutPersonalInfo validEntity) {
        return !this.voiceMnpOutId.equals(validEntity.getVoiceMnpOutId());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpOutPersonalInfo validEntity) {
        return this.equals(validEntity);
    }
}
