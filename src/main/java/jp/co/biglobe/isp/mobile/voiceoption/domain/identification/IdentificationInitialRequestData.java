package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceJoinCode;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceUserOrderDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class IdentificationInitialRequestData {

    @Getter
    private UserId userId;

    @Getter
    private LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private ReceiptStatus receiptStatus;

}
