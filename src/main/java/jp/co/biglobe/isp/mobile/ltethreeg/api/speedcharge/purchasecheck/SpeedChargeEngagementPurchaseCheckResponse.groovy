package jp.co.biglobe.isp.mobile.ltethreeg.api.speedcharge.purchasecheck

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchaseCheckStatus
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFeeFactory
import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpeedChargeEngagementPurchaseCheckResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(SpeedChargePurchaseCheckStatus speedChargePurchaseCheckStatus){

        Map resultMap = [
                "fee" : SpeedChargeFeeFactory.getApiValue(),
                "minPurchasedVolumeMB" : "100",
                "maxPurchasedVolumeMB" : "1000",
        ]

        checkApiResponse.build(speedChargePurchaseCheckStatus, resultMap);
    }

}
