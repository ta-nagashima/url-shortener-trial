package jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementdetailrefer

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntity
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.ValidVolumeChargeEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.ChargeEngagementDetailReferService.ChargeEngagementDetailReferContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChargeEngagementDetailReferResponse {

    @Autowired
    private JsonTemplate template;

    private final String CHARGE_RESULT = "chargeResult";

    public Map build(ChargeEngagementDetailReferContainer chargeEngagementDetailReferContainer) {

        LteThreeGEngagementEntity lteThreeGEngagementEntity = chargeEngagementDetailReferContainer.getLteThreeGEngagementEntity();
        ConnectPerformanceEntity connectPerformanceEntity = chargeEngagementDetailReferContainer.getConnectPerformanceEntity();
        SpeedChargeEngagementEntity speedChargeEngagementEntity = chargeEngagementDetailReferContainer.getSpeedChargeEngagementEntity();
        VolumeChargeEngagementEntity volumeChargeEngagementEntity = chargeEngagementDetailReferContainer.getVolumeChargeEngagementEntity();

        if(lteThreeGEngagementEntity.isInvalid()){
            return template.build( buildNotExist() );
        }
        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity = (ValidLteThreeGEngagementEntity)lteThreeGEngagementEntity;

        if(connectPerformanceEntity.isNotExist()){
            return template.build( buildNotExist() );
        }
        ValidConnectPerformanceEntity validConnectPerformanceEntity = (ValidConnectPerformanceEntity) connectPerformanceEntity;

        if(validLteThreeGEngagementEntity.isSpeedChargeValidPlan()){
            return template.build( buildSpeedChargeEngagement(validConnectPerformanceEntity, speedChargeEngagementEntity) );
        }

        return template.build( buildVolumeChargeEngagement(validConnectPerformanceEntity, volumeChargeEngagementEntity) );
    }

    private Map buildNotExist(){
        return [ (CHARGE_RESULT): StereotypedCharacters.NOT_EXIST ];
    }

    public Map buildSpeedChargeEngagement(ValidConnectPerformanceEntity validConnectPerformanceEntity, SpeedChargeEngagementEntity speedChargeEngagementEntity) {

        if (speedChargeEngagementEntity.isNotExist()) {
            return buildNotExist();
        }

        ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity = (ValidSpeedChargeEngagementEntity) speedChargeEngagementEntity;
        return [
                (CHARGE_RESULT): StereotypedCharacters.EXIST,
                "detail"      : [
                        "optionCode"                 : "OPTLTCC003",
                        "optionJapaneseName"         : "スピードチャージ",
                        "engagementNumber"           : validSpeedChargeEngagementEntity.getSpeedChargeEngagementNumber().getApiValue(),
                        "lteThreeGEngagementNumber"  : validSpeedChargeEngagementEntity.getLteThreeGEngagementNumber().getApiValue(),
                        "purchasedVolumeMB"          : validSpeedChargeEngagementEntity.getPurchasedVolumeMB().getApiValue(),
                        "orderDateTime"              : validSpeedChargeEngagementEntity.getSpeedChargeOrderDateTime().getApiValue(),
                        "applicationEndDateTime"     : validSpeedChargeEngagementEntity.getSpeedChargeApplicationEnd().getSpeedChargeApplicationEndDateTimeForApiValue(),
                        "completionStatus"           : validSpeedChargeEngagementEntity.getSpeedChargeCompletionStatus().getApiValue(),
                        "completionDetailStatus"     : validConnectPerformanceEntity.getSpeedStatus().getSpeedChargeStatus().getApiValue(),
                ]
        ];
    }

    public Map buildVolumeChargeEngagement(ValidConnectPerformanceEntity validConnectPerformanceEntity, VolumeChargeEngagementEntity volumeChargeEngagementEntity) {

        if (volumeChargeEngagementEntity.isNotExist()) {
            return buildNotExist();
        }

        ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity = (ValidVolumeChargeEngagementEntity) volumeChargeEngagementEntity;
        return [
                (CHARGE_RESULT): StereotypedCharacters.EXIST,
                "detail"      : [
                        "optionCode"                 : "OPTLTCC002",
                        "optionJapaneseName"         : "ボリュームチャージ",
                        "engagementNumber"           : validVolumeChargeEngagementEntity.getVolumeChargeEngagementNumber().getApiValue(),
                        "lteThreeGEngagementNumber"  : validVolumeChargeEngagementEntity.getLteThreeGEngagementNumber().getApiValue(),
                        "purchasedVolumeMB"          : validVolumeChargeEngagementEntity.getPurchasedVolumeMB().getApiValue(),
                        "orderDateTime"              : validVolumeChargeEngagementEntity.getVolumeChargeOrderDateTime().getApiValue(),
                        "applicationEndDateTime"     : validVolumeChargeEngagementEntity.getVolumeChargeApplicationEnd().getVolumeChargeApplicationEndDateTimeForApiValue(),
                        "completionStatus"           : validVolumeChargeEngagementEntity.getVolumeChargeCompletionStatus().getApiValue(),
                        "completionDetailStatus"     : validConnectPerformanceEntity.getSpeedStatus().getVolumeChargeStatus().getApiValue(),
                ]
        ]
    }

}
