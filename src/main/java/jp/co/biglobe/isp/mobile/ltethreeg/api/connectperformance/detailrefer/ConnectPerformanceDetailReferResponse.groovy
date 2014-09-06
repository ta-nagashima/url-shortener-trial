package jp.co.biglobe.isp.mobile.ltethreeg.api.connectperformance.detailrefer

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntity
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ConnectPerformanceDetailReferResponse {
    @Autowired
    private JsonTemplate template;

    public Map build(ConnectPerformanceEntity connectPerformanceEntity){

        final String CONNECT_PERFORMANCE_REFER_RESULT = "connectPerformanceReferResult";

        if (connectPerformanceEntity.isNotExist()) {
            return template.build([(CONNECT_PERFORMANCE_REFER_RESULT): StereotypedCharacters.NOT_EXIST]);
        }

        ValidConnectPerformanceEntity validConnectPerformanceEntity = (ValidConnectPerformanceEntity)connectPerformanceEntity;
        return template.build(
                [
                        (CONNECT_PERFORMANCE_REFER_RESULT): StereotypedCharacters.EXIST,
                ]
                +
                [
                        "connectPerformance": [
                            "lteThreeGEngagementNumber" : validConnectPerformanceEntity.getLteThreeGEngagementNumber().getApiValue(),
                            "limitStatus1Month"         : validConnectPerformanceEntity.getSpeedStatus().getLimitStatus1Month().getApiValue(),
                            "limitStatus72Hour"         : validConnectPerformanceEntity.getSpeedStatus().getLimitStatus72Hour().getApiValue(),
                            "volumeChargeStatus"        : validConnectPerformanceEntity.getSpeedStatus().getVolumeChargeStatus().getApiValue(),
                            "speedChargeStatus"         : validConnectPerformanceEntity.getSpeedStatus().getSpeedChargeStatus().getApiValue(),
                            "destinationStatus"         : validConnectPerformanceEntity.getDestinationStatus().getApiValue(),
                            "connectControlPolicy"      : validConnectPerformanceEntity.getConnectControlPolicyEntity().getConnectControlPolicy().getApiValue(),
                            "applicationDateTime"       : validConnectPerformanceEntity.getConnectControlPolicyEntity().getApplicationDateTime().getApiValue(),
                        ]
                ]
        )

    }

}
