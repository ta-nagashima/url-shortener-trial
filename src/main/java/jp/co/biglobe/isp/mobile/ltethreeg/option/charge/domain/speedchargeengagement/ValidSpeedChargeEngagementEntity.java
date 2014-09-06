package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end.NotExistSpeedChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end.SpeedChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end.ValidSpeedChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.end.ValidSpeedChargeApplicationEndFactory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidSpeedChargeEngagementEntity implements AfterSpeedChargeEngagementEntity, BeforeSpeedChargeEngagementEntity{

    @Getter
    private final SpeedChargeEngagementNumber speedChargeEngagementNumber;

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final PurchasedVolumeMB purchasedVolumeMB;

    @Getter
    private final SpeedChargeCompletionStatus speedChargeCompletionStatus;

    @Getter
    private final SpeedChargeOrderDateTime speedChargeOrderDateTime;

    @Getter
    private final SpeedChargeApplicationEnd speedChargeApplicationEnd;

    public ValidSpeedChargeEngagementEntity(
            SpeedChargeEngagementNumber speedChargeEngagementNumber,
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            PurchasedVolumeMB purchasedVolumeMB,
            SpeedChargeCompletionStatus speedChargeCompletionStatus,
            SpeedChargeOrderDateTime speedChargeOrderDateTime,
            ValidSpeedChargeApplicationEnd validSpeedChargeApplicationEnd
    ){

        this.speedChargeEngagementNumber = speedChargeEngagementNumber;
        this.lteThreeGEngagementNumber = lteThreeGEngagementNumber;
        this.purchasedVolumeMB = purchasedVolumeMB;
        this.speedChargeCompletionStatus = speedChargeCompletionStatus;
        this.speedChargeOrderDateTime = speedChargeOrderDateTime;
        this.speedChargeApplicationEnd = validSpeedChargeApplicationEnd == null
                ? new NotExistSpeedChargeApplicationEnd() : validSpeedChargeApplicationEnd;

    }

    public static ValidSpeedChargeEngagementEntity purchase(
            SpeedChargeEngagementNumber speedChargeEngagementNumber,
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        return new ValidSpeedChargeEngagementEntity(
                speedChargeEngagementNumber,
                lteThreeGEngagementNumber,
                SpeedChargePurchasedVolumeMBFactory.create(speedChargePurchasedVolumeNumber),
                SpeedChargeEvent.PURCHASE.getToStatus(),
                SpeedChargeOrderDateTime.create(),
                new NotExistSpeedChargeApplicationEnd()
        );

    }

    private ValidSpeedChargeEngagementEntity create(
            SpeedChargeCompletionStatus speedChargeCompletionStatus,
            SpeedChargeApplicationEnd speedChargeApplicationEnd){

        return new ValidSpeedChargeEngagementEntity(
                speedChargeEngagementNumber,
                lteThreeGEngagementNumber,
                purchasedVolumeMB,
                speedChargeCompletionStatus,
                speedChargeOrderDateTime,
                speedChargeApplicationEnd);

    }

    @Override
    public boolean isNotExist(){
        return false;
    }

    @Override
    public AfterSpeedChargeEngagementEntity planChange(){
        return completeCheckAndCreate(SpeedChargeEvent.PLAN_CHANGE);
    }

    @Override
    public AfterSpeedChargeEngagementEntity complete(){
        return completeCheckAndCreate(SpeedChargeEvent.COMPLETE);
    }

    private AfterSpeedChargeEngagementEntity completeCheckAndCreate(SpeedChargeEvent speedChargeEvent){

        if(speedChargeEvent.isNgFromStatus(speedChargeCompletionStatus)){
            throw new SystemCheckException("スピードチャージの状態不正", LteThreegAlarmIdentifier.DEFAULT);
        }
        return create(speedChargeEvent.getToStatus(), ValidSpeedChargeApplicationEndFactory.create());

    }

    @Override
    public SpeedChargePurchaseCheckStatus verifySpeedChargePurchase(){
        return speedChargeCompletionStatus.getSpeedChargePurchaseCheckStatus();
    }
}
