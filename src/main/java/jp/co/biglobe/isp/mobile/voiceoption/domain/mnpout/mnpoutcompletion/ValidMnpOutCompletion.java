package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.VoiceMnpOutId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpOutCompletion implements MnpOutCompletion, CommonValidEntity<ValidMnpOutCompletion> {

    @Getter
    private final VoiceMnpOutId voiceMnpOutId;

    private final MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate;

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
    public boolean requiredDeleteAndInsert(ValidMnpOutCompletion validEntity) {
        return !this.voiceMnpOutId.equals(validEntity.getVoiceMnpOutId());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpOutCompletion validEntity) {
        return this.equals(validEntity);
    }
}
