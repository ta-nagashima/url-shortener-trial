package jp.co.biglobe.isp.mobile.ltethreeg.service.charge;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.*;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchaseCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeChargeRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.*;
import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectAuthLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumeChargeEngagementPurchaseService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    @Autowired
    private VolumeChargeEngagementRepository volumeChargeEngagementRepository;

    @Autowired
    private VolumeChargeChargeRepository volumeChargeChargeRepository;

    @Autowired
    private VolumeChargeSendMailTransfer volumeChargeSendMailTransfer;

    public VolumeChargePurchaseCheckStatus check(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        LteThreeGEngagementEntity lteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        VolumeChargeEngagementEntity volumeChargeEngagementEntity = volumeChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return lteThreeGEngagementEntity.verifyVolumeChargePurchase(volumeChargeEngagementEntity);

    }


    public void purchase(LteThreeGEngagementNumber lteThreeGEngagementNumber,
                         VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {

        verify(lteThreeGEngagementNumber);

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        connectPerformanceUpdate(validLteThreeGEngagementEntity);

        volumeChargeRegister(validLteThreeGEngagementEntity, volumeChargePurchasedVolumeNumber);

        transfer(lteThreeGEngagementNumber, validLteThreeGEngagementEntity.getUserId(), volumeChargePurchasedVolumeNumber);

    }

    private void verify(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        VolumeChargePurchaseCheckStatus volumeChargePurchaseCheckStatus = check(lteThreeGEngagementNumber);

        if(volumeChargePurchaseCheckStatus.isNg()){
            throw new SystemCheckException(
                    "ボリュームチャージが購入できる状態ではない", LteThreegAlarmIdentifier.DEFAULT
            );
        }

    }

    private void connectPerformanceUpdate(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.volumeChargePurchase(validLteThreeGEngagementEntity);

        updateConnectAuthAllMaybe(afterConnectPerformanceEntity, validLteThreeGEngagementEntity);

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.VOLUME_CHARGE_PURCHASE);

    }

    private void volumeChargeRegister(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity,
                                      VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {

        ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity =
                volumeChargeEngagementRepository.create(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(), volumeChargePurchasedVolumeNumber);

        volumeChargeEngagementRepository.purchase(validVolumeChargeEngagementEntity);

        volumeChargeChargeRepository.register(validVolumeChargeEngagementEntity, validLteThreeGEngagementEntity.getUserId(), volumeChargePurchasedVolumeNumber);

    }

    private void transfer(LteThreeGEngagementNumber lteThreeGEngagementNumber, UserId userId, VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {

        ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity = volumeChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        volumeChargeSendMailTransfer.send(validVolumeChargeEngagementEntity, userId, volumeChargePurchasedVolumeNumber);
    }


    private void updateConnectAuthAllMaybe(AfterConnectPerformanceEntity afterConnectPerformanceEntity, ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {
        if (afterConnectPerformanceEntity.isNoLimitedForVolume()) {
            return;
        }
        connectAuthService.updateAll(
                afterConnectPerformanceEntity, validLteThreeGEngagementEntity);
    }
}
