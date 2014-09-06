package jp.co.biglobe.isp.mobile.ltethreeg.service.charge;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.*;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeChargeRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.*;
import jp.co.biglobe.isp.mobile.ltethreeg.service.ConnectAuthLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedChargeEngagementPurchaseService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private SpeedChargeEngagementRepository speedChargeEngagementRepository;

    @Autowired
    private SpeedChargeChargeRepository speedChargeChargeRepository;

    @Autowired
    private SpeedChargeSendMailTransfer speedChargeSendMailTransfer;

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private ConnectAuthLocalService connectAuthService;

    public SpeedChargePurchaseCheckStatus check(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        LteThreeGEngagementEntity lteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        SpeedChargeEngagementEntity speedChargeEngagementEntity = speedChargeEngagementRepository.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        return lteThreeGEngagementEntity.verifySpeedChargePurchase(speedChargeEngagementEntity);

    }


    public void purchase(LteThreeGEngagementNumber lteThreeGEngagementNumber, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        verify(lteThreeGEngagementNumber);

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity = lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        speedChargeRegister(validLteThreeGEngagementEntity, speedChargePurchasedVolumeNumber);

        connectPerformanceUpdate(validLteThreeGEngagementEntity);

        transfer(lteThreeGEngagementNumber, validLteThreeGEngagementEntity.getUserId(), speedChargePurchasedVolumeNumber);

    }

    private void verify(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        SpeedChargePurchaseCheckStatus speedChargePurchaseCheckStatus = check(lteThreeGEngagementNumber);

        if(speedChargePurchaseCheckStatus.isNg()){
            throw new SystemCheckException(
                    "スピードチャージが購入できる状態ではない", LteThreegAlarmIdentifier.DEFAULT
            );
        }

    }

    private void speedChargeRegister(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity = speedChargeEngagementRepository.create(
                validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(), speedChargePurchasedVolumeNumber);

        speedChargeEngagementRepository.insert(validSpeedChargeEngagementEntity);

        speedChargeChargeRepository.register(validSpeedChargeEngagementEntity, validLteThreeGEngagementEntity.getUserId(), speedChargePurchasedVolumeNumber);

    }

    private void connectPerformanceUpdate(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        BeforeConnectPerformanceEntity beforeConnectPerformanceEntity = connectPerformanceRepository.findByLteThreeGEngagementNumberForValid(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());

        AfterConnectPerformanceEntity afterConnectPerformanceEntity = beforeConnectPerformanceEntity.speedChargePurchase();

        connectPerformanceRepository.update(beforeConnectPerformanceEntity, afterConnectPerformanceEntity, ConnectPerformanceEvent.SPEED_CHARGE_PURCHASE);

        connectAuthService.updateAll(afterConnectPerformanceEntity, validLteThreeGEngagementEntity);
    }

    private void transfer(LteThreeGEngagementNumber lteThreeGEngagementNumber, UserId userId, SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity = speedChargeEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);

        speedChargeSendMailTransfer.purchase(validSpeedChargeEngagementEntity, userId, speedChargePurchasedVolumeNumber);

    }

}
