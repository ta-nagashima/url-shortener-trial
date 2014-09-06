package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end.NoExistVolumeChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end.ValidVolumeChargeApplicationEnd;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end.ValidVolumeChargeApplicationEndFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.end.VolumeChargeApplicationEnd;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVolumeChargeEngagementEntity implements BeforeVolumeChargeEngagementEntity, AfterVolumeChargeEngagementEntity{

    @Getter
    private final VolumeChargeEngagementNumber volumeChargeEngagementNumber;

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final PurchasedVolumeMB purchasedVolumeMB;

    @Getter
    private final VolumeChargeCompletionStatus volumeChargeCompletionStatus;

    @Getter
    private final VolumeChargeOrderDateTime volumeChargeOrderDateTime;

    @Getter
    private final VolumeChargeApplicationEnd volumeChargeApplicationEnd;

    public ValidVolumeChargeEngagementEntity(
            VolumeChargeEngagementNumber volumeChargeEngagementNumber,
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            PurchasedVolumeMB purchasedVolumeMB,
            VolumeChargeCompletionStatus volumeChargeCompletionStatus,
            VolumeChargeOrderDateTime volumeChargeOrderDateTime,
            ValidVolumeChargeApplicationEnd volumeChargeApplicationEnd){

        this.volumeChargeEngagementNumber = volumeChargeEngagementNumber;
        this.lteThreeGEngagementNumber = lteThreeGEngagementNumber;
        this.purchasedVolumeMB = purchasedVolumeMB;
        this.volumeChargeCompletionStatus = volumeChargeCompletionStatus;
        this.volumeChargeOrderDateTime = volumeChargeOrderDateTime;
        this.volumeChargeApplicationEnd = volumeChargeApplicationEnd == null ? new NoExistVolumeChargeApplicationEnd() : volumeChargeApplicationEnd;
    }

    public static ValidVolumeChargeEngagementEntity purchase(
            VolumeChargeEngagementNumber volumeChargeEngagementNumber,
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber){

        return new ValidVolumeChargeEngagementEntity(
                volumeChargeEngagementNumber,
                lteThreeGEngagementNumber,
                PurchasedVolumeMBFactory.create(volumeChargePurchasedVolumeNumber),
                VolumeChargeEvent.PURCHASE.getToStatus(),
                VolumeChargeOrderDateTime.create(),
                new NoExistVolumeChargeApplicationEnd());
    }

    private AfterVolumeChargeEngagementEntity create(
            VolumeChargeCompletionStatus volumeChargeCompletionStatus,
            VolumeChargeApplicationEnd volumeChargeApplicationEnd){

        return new ValidVolumeChargeEngagementEntity(
                volumeChargeEngagementNumber,
                lteThreeGEngagementNumber,
                purchasedVolumeMB,
                volumeChargeCompletionStatus,
                volumeChargeOrderDateTime,
                volumeChargeApplicationEnd
        );

    }

    @Override
    public boolean isNotExist(){
        return false;
    }


    public AfterVolumeChargeEngagementEntity planChange(){
        return completeCheckAndCreate(VolumeChargeEvent.PLAN_CHANGE);
    }

    public AfterVolumeChargeEngagementEntity start(){
        return completeCheckAndCreate(VolumeChargeEvent.COMPLETE);
    }

    public AfterVolumeChargeEngagementEntity complete(){
        return completeCheckAndCreate(VolumeChargeEvent.COMPLETE);
    }

    private AfterVolumeChargeEngagementEntity completeCheckAndCreate(VolumeChargeEvent volumeChargeEvent){

        if(volumeChargeEvent.isNgFromStatus(volumeChargeCompletionStatus)){
            throw new SystemCheckException("ボリュームチャージの状態不正", LteThreegAlarmIdentifier.DEFAULT);
        }
        return create(volumeChargeEvent.getToStatus(), ValidVolumeChargeApplicationEndFactory.create());

    }

    @Override
    public VolumeChargePurchaseCheckStatus verifyVolumeChargePurchase(){
        return volumeChargeCompletionStatus.getVolumeChargePurchaseCheckStatus();
    }

}
