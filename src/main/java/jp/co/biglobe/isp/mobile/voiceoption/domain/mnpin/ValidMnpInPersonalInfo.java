package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpInPersonalInfo implements MnpInPersonalInfo, CommonValidEntity<ValidMnpInPersonalInfo> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    private final MnpFullName mnpFullName;

    private final MnpFullNameKana mnpFullNameKana;

    private final MnpGender mnpGender;

    private final MnpBirthday mnpBirthday;

    public ValidMnpInPersonalInfo(
            VoiceEngagementNumber voiceEngagementNumber,
            ValidMnpPersonalInfo validMnpPersonalInfo) {
        this.voiceEngagementNumber = voiceEngagementNumber;
        this.mnpFullName = validMnpPersonalInfo.getMnpFullName();
        this.mnpFullNameKana = validMnpPersonalInfo.getMnpFullNameKana();
        this.mnpGender = validMnpPersonalInfo.getMnpGender();
        this.mnpBirthday = validMnpPersonalInfo.getMnpBirthday();
    }

    @Override
    public String getMnpFullNameForApiValue(){
        return this.mnpFullName.getApiValue();
    }

    @Override
    public String getMnpFullNameKanaForApiValue(){
        return this.mnpFullNameKana.getApiValue();
    }

    @Override
    public String getMnpGenderForApiValue(){
        return this.mnpGender.getApiValue();
    }

    @Override
    public String getMnpBirthdayForApiValue(){
        return this.mnpBirthday.getApiValue();
    }

    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpInPersonalInfo validEntity) {
        // 外部参照キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validEntity.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpInPersonalInfo validEntity) {
        return this.equals(validEntity);
    }
}
