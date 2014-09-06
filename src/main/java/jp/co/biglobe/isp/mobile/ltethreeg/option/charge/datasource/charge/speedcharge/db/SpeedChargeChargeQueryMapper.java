package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge.db;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeItemCode;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface SpeedChargeChargeQueryMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("speedChargeEngagementNumber") SpeedChargeEngagementNumber speedChargeEngagementNumber,
            @Param("operatorId") OperatorId operatorId,
            @Param("userId") UserId userId,
            @Param("speedChargeItemCode") SpeedChargeItemCode speedChargeItemCode,
            @Param("speedChargeFee") SpeedChargeFee speedChargeFee
    );

}
