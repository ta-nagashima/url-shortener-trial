package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.AfterSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeCompletionStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SpeedChargeEngagementRepositoryQueryMapper {

    public void insertState(
            @Param("eventDate") Date eventDate,
            @Param("speedChargeEngagementEntity") AfterSpeedChargeEngagementEntity speedChargeEngagementEntity
    );

    public void insertPurchaseEvent(
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("speedChargeEngagementEntity") AfterSpeedChargeEngagementEntity speedChargeEngagementEntity
    );

    public void insertCompleteEvent(
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("speedChargeEngagementEntity") AfterSpeedChargeEngagementEntity speedChargeEngagementEntity
    );

    public void updateStateForPurchase(
            @Param("eventDate") Date eventDate,
            @Param("speedChargeEngagementEntity") AfterSpeedChargeEngagementEntity speedChargeEngagementEntity
    );

    public void updateState(
            @Param("eventDate") Date eventDate,
            @Param("speedChargeEngagementEntity") AfterSpeedChargeEngagementEntity speedChargeEngagementEntity
    );


    public ValidSpeedChargeEngagementEntity findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public List<ValidSpeedChargeEngagementEntity> findAllByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("remained") SpeedChargeCompletionStatus remained,
            @Param("completion") SpeedChargeCompletionStatus completion
    );

}
