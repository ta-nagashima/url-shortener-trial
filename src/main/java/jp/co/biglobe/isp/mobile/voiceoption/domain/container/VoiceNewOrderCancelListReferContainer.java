package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGOrderType;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceOrderType;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class VoiceNewOrderCancelListReferContainer {

    private final ValidVoiceEngagement validVoiceEngagement;

    private final ValidIdentification validIdentification;

    private final ValidLteThreeGEngagementEntity validLteThreeGEngagement;

    public String getUserIdApiValue() {
        return validVoiceEngagement.getVoiceEngagementLinkage().getUserId().getApiValue();
    }

    public String getLteThreeGEngagementNumberApiValue(){
        return validVoiceEngagement.getVoiceEngagementLinkage().getLteThreeGEngagementNumber().getApiValue();
    }

    public String getVoiceUserOrderDateApiValue() {
        return validVoiceEngagement.getNewOrderInfo().getVoiceUserOrderDate().getApiValue();
    }

    public String getNgDateApiValue() {

        if (validIdentification.getIdentificationStatus() == IdentificationStatus.OUTBOUND_NOW) {
            return validIdentification.getIdentificationResult().getExcutionDateForApiValue();
        }

        return "";
    }

    public String getCustomerOrderStatusApiValue() {

        if (validLteThreeGEngagement.getLteThreeGOrderType() == LteThreeGOrderType.SINGLE_ORDER) {
            return CustomerOrderStatus.SINGLE_ORDER_OR_CHANGE_DATA_TO_VOICE.getNoRefactoringValue();
        }

        if (validVoiceEngagement.getVoiceOrderType() == VoiceOrderType.CHANGE_DATA_TO_VOICE) {
            return CustomerOrderStatus.SINGLE_ORDER_OR_CHANGE_DATA_TO_VOICE.getNoRefactoringValue();
        }

        return CustomerOrderStatus.ORDER_AND_JOIN.getNoRefactoringValue();

    }

    @AllArgsConstructor
    private enum CustomerOrderStatus implements ApiValue {
        ORDER_AND_JOIN("新規"),
        SINGLE_ORDER_OR_CHANGE_DATA_TO_VOICE("既存");

        @Getter
        private final String noRefactoringValue;
    }

}
