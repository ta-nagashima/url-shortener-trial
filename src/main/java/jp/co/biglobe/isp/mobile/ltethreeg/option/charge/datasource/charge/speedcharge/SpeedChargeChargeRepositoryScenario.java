package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge.db.SpeedChargeChargeQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge.scenario.SpeedChargeChargeScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeChargeRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFeeFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedChargeChargeRepositoryScenario implements SpeedChargeChargeRepository {

    @Autowired
    SpeedChargeChargeQueryMapper speedChargeChargeQueryMapper;

    @Autowired
    SpeedChargeChargeScenario speedChargeChargeScenario;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Autowired
    private PropertyAccessor propertyAccessor;

    public void register(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity,
                         UserId userId,
                         SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber){

        SpeedChargeItemCode speedChargeItemCode = new SpeedChargeItemCode();
        SpeedChargeFee speedChargeFee = SpeedChargeFeeFactory.create(speedChargePurchasedVolumeNumber);
        OperatorId operatorId = new OperatorId(propertyAccessor.getProperty("fixation.operator.id"));

        speedChargeChargeQueryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validSpeedChargeEngagementEntity.getSpeedChargeEngagementNumber(),
                operatorId,
                userId,
                speedChargeItemCode,
                speedChargeFee
        );

        speedChargeChargeScenario.register(operatorId, userId, speedChargeItemCode, speedChargeFee, requestEventTime);
    }

}
