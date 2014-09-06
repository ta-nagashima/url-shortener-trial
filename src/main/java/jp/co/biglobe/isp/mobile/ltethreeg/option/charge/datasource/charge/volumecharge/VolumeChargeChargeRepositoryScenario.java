package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge.db.VolumeChargeChargeQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge.scenario.VolumeChargeChargeScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeChargeRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFeeFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.ValidVolumeChargeEngagementEntity;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VolumeChargeChargeRepositoryScenario implements VolumeChargeChargeRepository {

    @Autowired
    VolumeChargeChargeQueryMapper volumeChargeChargeQueryMapper;

    @Autowired
    VolumeChargeChargeScenario volumeChargeChargeScenario;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Autowired
    private PropertyAccessor propertyAccessor;

    public void register(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity,UserId userId,VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber){

        VolumeChargeItemCode volumeChargeItemCode = new VolumeChargeItemCode();
        VolumeChargeFee volumeChargeFee = VolumeChargeFeeFactory.create(volumeChargePurchasedVolumeNumber);
        OperatorId operatorId = new OperatorId(propertyAccessor.getProperty("fixation.operator.id"));

        volumeChargeChargeQueryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validVolumeChargeEngagementEntity.getVolumeChargeEngagementNumber(),
                operatorId,
                userId,
                volumeChargeItemCode,
                volumeChargeFee
        );

        volumeChargeChargeScenario.register(operatorId, userId, volumeChargeItemCode,volumeChargeFee, requestEventTime);
    }

}
