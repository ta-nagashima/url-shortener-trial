package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceEngagementLinkage {

    @Getter
    private final UserId userId;

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final EquipmentNumber equipmentNumber;

    public VoiceEngagementLinkage(VoiceEngagementInitialRequestData voiceEngagementInitialRequestData){
        this.userId = voiceEngagementInitialRequestData.getUserId();
        this.lteThreeGEngagementNumber = voiceEngagementInitialRequestData.getLteThreeGEngagementNumber();
        this.equipmentNumber = voiceEngagementInitialRequestData.getEquipmentNumber();
    }

    VoiceEngagementLinkage simChange(EquipmentNumber newNumber){
        return new VoiceEngagementLinkage(userId, lteThreeGEngagementNumber, newNumber);
    }
}
