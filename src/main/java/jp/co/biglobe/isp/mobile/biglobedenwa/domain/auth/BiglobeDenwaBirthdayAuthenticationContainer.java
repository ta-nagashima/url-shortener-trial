package jp.co.biglobe.isp.mobile.biglobedenwa.domain.auth;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.policy.BiglobeDenwaBirthDayCheckStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeDenwaBirthdayAuthenticationContainer {

    private final BiglobeDenwaBirthday registedBiglobeDenwaBirthday;

    private final LteThreeGEngagementEntity lteThreeGEngagementEntity;

    private final BiglobeDenwaLinkage biglobeDenwaLinkage;

    public BiglobeDenwaBirthDayCheckStatus auth(BiglobeDenwaBirthday inputBiglobeDenwaBirthday) {
        if (lteThreeGEngagementEntity.isInvalid()) {
            return BiglobeDenwaBirthDayCheckStatus.UNREGISTERED_LTE3G;
        }

        if(biglobeDenwaLinkage.isNotExist()){
            return BiglobeDenwaBirthDayCheckStatus.UNREGISTERED_DENWA;
        }

        if (registedBiglobeDenwaBirthday.equals(inputBiglobeDenwaBirthday)) {
            return BiglobeDenwaBirthDayCheckStatus.OK;
        }

        return BiglobeDenwaBirthDayCheckStatus.NG;
    }
}
