package jp.co.biglobe.isp.mobile.ltethreeg.datasource.ltethreegengagement.db;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LteThreeGEngagementQueryMapper {

    public ValidLteThreeGEngagementEntity findByUserId(
            @Param("userId") UserId userId,
            @Param("systemdate") Date systemdate
    );

    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumberForEnable(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber,
            @Param("systemdate") Date systemdate
    );


    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumber(
            @Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber
    );

    public ValidLteThreeGEngagementEntity findVoiceSimEngagementByMsisdn(
            @Param("msisdn") Msisdn msisdn,
            @Param("systemdate") Date systemdate
    );

    public ValidLteThreeGEngagementEntity findVoiceSimEngagementByBiglobeDenwaMsisdn(
            @Param("msisdn") BiglobeDenwaMsisdn msisdn,
            @Param("systemdate") Date systemdate
    );

}
