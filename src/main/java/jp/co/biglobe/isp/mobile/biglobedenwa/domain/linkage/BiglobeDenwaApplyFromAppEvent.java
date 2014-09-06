package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.isp.mobile.extension.exception.BusinessException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum BiglobeDenwaApplyFromAppEvent {

    /**
     * 2014年8月7日時点では、STATEテーブルにNO_LINKAGEの状態が登録される事はないので、
     * NO_LINKAGEの状態のオブジェクトが生成されるユースケースはないが、
     * EVENTテーブルにはNO_LINKAGEの状態で記録され鋳るため、
     * 今後の機能拡張でNO_LINKGE状態のオブジェクトが生成、使用されることも考慮して、
     * NO_LINKAGEのときにWAITING_REGISTERイベントを取得できるようにしている。
     */

    APPLAY_FROM_APP(new HashMap<BiglobeDenwaLinkageStatus, BiglobeDenwaLinkageEvent>() {{
        put(BiglobeDenwaLinkageStatus.REGISTERED_IN_OTHER, BiglobeDenwaLinkageEvent.WAITING_REGISTER);
        put(BiglobeDenwaLinkageStatus.UNABLE_TO_REGISTER, BiglobeDenwaLinkageEvent.WAITING_REREGISTER);
        put(BiglobeDenwaLinkageStatus.NO_LINKAGE,BiglobeDenwaLinkageEvent.WAITING_REGISTER);
    }});

    private final Map<BiglobeDenwaLinkageStatus, BiglobeDenwaLinkageEvent> event;

    public BiglobeDenwaLinkageEvent toStatus(BiglobeDenwaLinkageStatus before) {
        return Optional.ofNullable(event.get(before))
                .orElseThrow(() -> new BusinessException(
                        "でんわ連携のステータスが異常",
                        BiglobeDenwaRegisterErrorStatus.INVALID_STATUS,
                        VoiceOptionAlarmIdentifier.DEFAULT
                ));
    }
}
