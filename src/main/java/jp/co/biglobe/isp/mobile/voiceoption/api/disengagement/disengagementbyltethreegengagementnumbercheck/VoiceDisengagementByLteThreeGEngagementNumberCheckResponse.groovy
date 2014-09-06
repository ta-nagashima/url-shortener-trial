package jp.co.biglobe.isp.mobile.voiceoption.api.disengagement.disengagementbyltethreegengagementnumbercheck

import jp.co.biglobe.isp.mobile.extension.response.normal.MultiCheckApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge.DisengagementCharge
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.DisengagementCheckStatusContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VoiceDisengagementByLteThreeGEngagementNumberCheckResponse {

    @Autowired
    private MultiCheckApiResponse multiCheckApiResponse;

    public Map build(DisengagementCheckStatusContainer disengagementCheckStatusSet, DisengagementCharge disengagementCharge) {

        Map statusSet = [
                "disengagement" : disengagementCheckStatusSet.getDisengagementCheckStatus(),
                "mnp_out_completion_and_disengagement" : disengagementCheckStatusSet.getMnpOutAndDisengagementCheckStatus()
        ]

        Map body = [
                "disengagement_charge" : [
                        "status" : disengagementCharge.getApiValueForCostingStatus(),
                        "end_month" : disengagementCharge.getApiValueForCostingEndMonth(),
                        "amount" : disengagementCharge.getApiValueForCostingAmount()
                ]
        ]


        multiCheckApiResponse.build(statusSet, body);
    }
}
