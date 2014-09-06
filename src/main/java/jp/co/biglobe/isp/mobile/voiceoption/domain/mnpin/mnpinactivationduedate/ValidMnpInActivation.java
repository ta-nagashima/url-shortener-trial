package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpInActivation implements MnpInActivation, CommonValidEntity<ValidMnpInActivation> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    @Getter
    private final ValidMnpInActivationDueDate mnpInActivationDueDate;

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public String getMnpActivationDueDateForApiValue() {
        return mnpInActivationDueDate.getApiValue();
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpInActivation validEntity) {
        // 外部参照キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validEntity.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpInActivation validEntity) {
        return this.equals(validEntity);
    }
}
