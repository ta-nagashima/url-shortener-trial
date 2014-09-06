package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer;

import jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario.BiglobeDenwaPersonalInfoScenario;
import jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario.BiglobeDenwaPersonalInfoScenarioOutput;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthdayAuthenticationContainer;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth.BiglobeDenwaBirthdayAuthenticationContainerRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BiglobeDenwaBirthdayAuthenticationContainerRepositoryScenario implements BiglobeDenwaBirthdayAuthenticationContainerRepository {

    @Autowired
    private BiglobeDenwaPersonalInfoScenario biglobeDenwaPersonalInfoScenario;



    @Override
    public BiglobeDenwaBirthdayAuthenticationContainer createAuthenticationContainer(LteThreeGEngagementEntity lteThreeGEngagementEntity, BiglobeDenwaLinkage biglobeDenwaLinkage) {
        if (lteThreeGEngagementEntity.isInvalid()) {
            return new BiglobeDenwaBirthdayAuthenticationContainer(null, lteThreeGEngagementEntity,biglobeDenwaLinkage);
        }

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity = (ValidLteThreeGEngagementEntity) lteThreeGEngagementEntity;

        BiglobeDenwaPersonalInfoScenarioOutput biglobeDenwaPersonalInfoScenarioOutput = biglobeDenwaPersonalInfoScenario.findByBiglobeId(validLteThreeGEngagementEntity.getUserId());

        return new BiglobeDenwaBirthdayAuthenticationContainer(biglobeDenwaPersonalInfoScenarioOutput.convertBirthDayStringToBiglobeDenwaBirthDay(),validLteThreeGEngagementEntity,biglobeDenwaLinkage);
    }

}
