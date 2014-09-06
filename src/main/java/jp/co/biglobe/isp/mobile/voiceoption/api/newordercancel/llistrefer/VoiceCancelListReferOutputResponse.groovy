package jp.co.biglobe.isp.mobile.voiceoption.api.newordercancel.llistrefer

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceNewOrderCancelListReferContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by takaosakamoto on 2014/07/11.
 */
@Component
class VoiceCancelListReferOutputResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(VoiceNewOrderCancelListReferContainer voiceNewOrderCancelListReferContainer) {
        return template.build(
                [
                "voiceEngagement":[
                        "userId": voiceNewOrderCancelListReferContainer.getUserIdApiValue(),
                        "lteThreeGEngagementNumber":voiceNewOrderCancelListReferContainer.getLteThreeGEngagementNumberApiValue(),
                        "voiceUserOrderDate":voiceNewOrderCancelListReferContainer.getVoiceUserOrderDateApiValue(),
                        "customerOrderStatus":voiceNewOrderCancelListReferContainer.getCustomerOrderStatusApiValue()
                        ],
                "identificationResult":[
                        "NgDate":voiceNewOrderCancelListReferContainer.getNgDateApiValue(),
                        ]

                ]
        );
    }
}
