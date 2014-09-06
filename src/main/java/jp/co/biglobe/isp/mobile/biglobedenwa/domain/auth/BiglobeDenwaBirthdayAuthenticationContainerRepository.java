package jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;

public interface BiglobeDenwaBirthdayAuthenticationContainerRepository {

    public BiglobeDenwaBirthdayAuthenticationContainer createAuthenticationContainer(LteThreeGEngagementEntity lteThreeGEngagementEntity, BiglobeDenwaLinkage biglobeDenwaLinkage);

}
