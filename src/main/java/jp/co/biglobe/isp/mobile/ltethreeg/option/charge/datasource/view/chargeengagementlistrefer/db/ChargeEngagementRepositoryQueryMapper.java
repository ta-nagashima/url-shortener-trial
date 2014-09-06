package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.view.chargeengagementlistrefer.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.AfterSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargeCompletionStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntityTotalCount;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChargeEngagementRepositoryQueryMapper {

    public ChargeEngagementEntityTotalCount findByLteThreeGEngagementNumberForCount(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public List<ChargeEngagementEntity> findAllByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("row") Integer row,
            @Param("high") Integer high
    );

}
