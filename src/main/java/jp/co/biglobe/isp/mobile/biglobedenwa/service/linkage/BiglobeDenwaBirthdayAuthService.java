package jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthday;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthdayAuthenticationContainer;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthdayAuthenticationContainerRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.policy.BiglobeDenwaBirthDayCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiglobeDenwaBirthdayAuthService {

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private BiglobeDenwaBirthdayAuthenticationContainerRepository biglobeDenwaBirthDayAuthenticationContainerRepository;

    public BiglobeDenwaBirthDayCheckStatus auth(BiglobeDenwaMsisdn biglobeDenwaMsisdn, BiglobeDenwaBirthday biglobeDenwaBirthday,BiglobeDenwaLinkage biglobeDenwaLinkage) {

        LteThreeGEngagementEntity lteThreeGEngagementEntity = lteThreeGEngagementRepository.findVoiceSimEngagementByBiglobeDenwaMsisdn(biglobeDenwaMsisdn);

        BiglobeDenwaBirthdayAuthenticationContainer biglobeDenwaBirthdayAuthenticationContainer =
                biglobeDenwaBirthDayAuthenticationContainerRepository.createAuthenticationContainer(lteThreeGEngagementEntity, biglobeDenwaLinkage);

        return biglobeDenwaBirthdayAuthenticationContainer.auth(biglobeDenwaBirthday);
    }


}
