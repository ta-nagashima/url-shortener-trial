package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.volumecharge.db;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargeEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface VolumeChargeChargeQueryMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("volumeChargeEngagementNumber") VolumeChargeEngagementNumber volumeChargeEngagementNumber,
            @Param("operatorId") OperatorId operatorId,
            @Param("userId") UserId userId,
            @Param("volumeChargeItemCode") VolumeChargeItemCode volumeChargeItemCode,
            @Param("volumeChargeFee") VolumeChargeFee volumeChargeFee
    );

}
