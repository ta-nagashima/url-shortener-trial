package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.AfterVolumeChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.ValidVolumeChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeCompletionStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface VolumeChargeEngagementRepositoryQueryMapper {

    public void insertVolumeState(
            @Param("eventDate") Date eventDate,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity validVolumeChargeEngagementEntity
    );

    public void insertPurchaseEvent(
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity validVolumeChargeEngagementEntity
    );

    public void insertCompleteEvent(
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity validVolumeChargeEngagementEntity
    );

    public void insertCompletePlanChangeEvent(
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity validVolumeChargeEngagementEntity
    );

    public void updateCompleteState(
            @Param("eventDate") Date eventDate,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity volumeChargeEngagementEntity
    );

    public void updateStateForPurchase(
            @Param("eventDate") Date eventDate,
            @Param("volumeChargeEngagementEntity") AfterVolumeChargeEngagementEntity volumeChargeEngagementEntity
    );

    public ValidVolumeChargeEngagementEntity findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public List<ValidVolumeChargeEngagementEntity> findAllByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("remained") VolumeChargeCompletionStatus remained,
            @Param("completion") VolumeChargeCompletionStatus completion
    );


}
