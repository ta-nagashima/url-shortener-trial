package jp.co.biglobe.isp.mobile.ltethreeg.api.volumecharge.purchasecheck

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFeeFactory
import jp.co.biglobe.lib.plugin.response.normal.CheckApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VolumeChargeEngagementPurchaseCheckResponse {

    @Autowired
    private CheckApiResponse checkApiResponse;

    public Map build(VolumeChargePurchaseCheckStatus volumeChargePurchaseCheckStatus){

        Map resultMap = [
                "fee" : VolumeChargeFeeFactory.getApiValue(),
                "minPurchasedVolumeMB" : "100",
                "maxPurchasedVolumeMB" : "1000",
        ]

        checkApiResponse.build(volumeChargePurchaseCheckStatus, resultMap);
    }

}
