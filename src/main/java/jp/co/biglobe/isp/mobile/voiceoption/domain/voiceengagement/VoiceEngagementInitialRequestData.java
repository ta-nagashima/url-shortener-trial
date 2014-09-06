package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;


import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class VoiceEngagementInitialRequestData {

    @Getter
    VoiceUserOrderDate voiceUserOrderDate;

    @Getter
    VoiceJoinCode voiceJoinCode;

    @Getter
    EquipmentNumber equipmentNumber;

    @Getter
    UserId userId;

    @Getter
    LteThreeGEngagementNumber lteThreeGEngagementNumber;

}
