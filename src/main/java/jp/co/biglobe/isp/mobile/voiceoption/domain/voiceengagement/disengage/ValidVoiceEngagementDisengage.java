package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage;

import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@lombok.ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVoiceEngagementDisengage implements VoiceEngagementDisengage, CommonValidEntity<ValidVoiceEngagementDisengage> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    private final VoiceEngagementDisengageReason voiceEngagementDisengageReason;

    private final VoiceEngagementDisengageSystemReceiptDateTime voiceEngagementDisengageSystemReceiptDateTime;

    private final VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate;

    @Override
    public ValidVoiceEngagementDisengage disengage(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementDisengageReason voiceEngagementDisengageReason,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate) {
        return new ValidVoiceEngagementDisengage(
                voiceEngagementNumber,
                voiceEngagementDisengageReason,
                new VoiceEngagementDisengageSystemReceiptDateTime(new SystemDateTime().getValue()),
                voiceEngagementDisengageOrderDate);
    }

    @Override
    public boolean isExist(){
        return true;
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidVoiceEngagementDisengage validVoiceEngagementDisengage) {
        // 外部参照キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validVoiceEngagementDisengage.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidVoiceEngagementDisengage o) {
        return this.equals(o);
    }
}
