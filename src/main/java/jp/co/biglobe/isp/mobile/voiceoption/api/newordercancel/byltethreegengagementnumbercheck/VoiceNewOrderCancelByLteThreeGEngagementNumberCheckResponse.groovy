package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.byltethreegengagementnumbercheck

import jp.co.biglobe.isp.mobile.extension.response.normal.MultiCheckApiResponse
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.NewOrderCancelCheckStatusContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 新規申込キャンセル可否チェック(LTE3G契約番号)
 */
@Component
class VoiceNewOrderCancelByLteThreeGEngagementNumberCheckResponse {

    @Autowired
    private MultiCheckApiResponse multiCheckApiResponse;

    public Map build(NewOrderCancelCheckStatusContainer newOrderCancelCheckStatusSet) {
        Map statusSet = [
                "cancel" : newOrderCancelCheckStatusSet.getNewOrderCancelCheckStatus(),
                "mnp_out_completion_and_cancel" : newOrderCancelCheckStatusSet.getMnpOutAndNewOrderCancelCheckStatus()
        ]

        multiCheckApiResponse.build(statusSet);
    }
}
