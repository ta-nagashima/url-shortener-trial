package jp.co.biglobe.isp.mobile.ltethreeg.datasource.sim.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SimRepositoryQueryMapper {

    public List<ValidSimEntity> findByLteThreeGEngagementNumberUnderValid(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("systemdate") Date systemDate
    );

    /**
     * 指定した月に利用可能なSIMのリストを取得
     */

    public List<ValidSimEntity> findByUserIdAndTargetMonthUnderValid(
            @Param("userId") UserId userId,
            @Param("targetMonth") TargetMonth targetDate
    );
}
