package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel;

import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@lombok.ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVoiceEngagementCancel implements VoiceEngagementCancel, CommonValidEntity<ValidVoiceEngagementCancel> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    private final VoiceEngagementCancelReason voiceEngagementCancelReason;

    private final VoiceEngagementCancelSystemReceiptDateTime voiceEngagementCancelSystemReceiptDateTime;

    private final VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate;

    @Override
    public ValidVoiceEngagementCancel cancel(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate) {
        return new ValidVoiceEngagementCancel(
                voiceEngagementNumber,
                voiceEngagementCancelReason,
                new VoiceEngagementCancelSystemReceiptDateTime(new SystemDateTime().getValue()),
                voiceEngagementCancelOrderDate);
    }

    @Override
    public boolean isExist(){
        return true;
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidVoiceEngagementCancel validVoiceEngagementCancel) {
        // 外部参照キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validVoiceEngagementCancel.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidVoiceEngagementCancel validEntity) {
        return this.equals(validEntity);
    }
}
