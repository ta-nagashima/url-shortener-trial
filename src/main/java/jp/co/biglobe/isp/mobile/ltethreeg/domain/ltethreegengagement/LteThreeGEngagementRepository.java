package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;

public interface LteThreeGEngagementRepository {

    public LteThreeGEngagementEntity findByUserId(UserId userId);

    public ValidLteThreeGEngagementEntity findByUserIdForValid(UserId userId);

    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumberForValidForEnable(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public LteThreeGEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public LteThreeGEngagementEntity findVoiceSimEngagementByMsisdn(Msisdn msisdn);

    public LteThreeGEngagementEntity findVoiceSimEngagementByBiglobeDenwaMsisdn(BiglobeDenwaMsisdn biglobeDenwaMsisdn);
}
