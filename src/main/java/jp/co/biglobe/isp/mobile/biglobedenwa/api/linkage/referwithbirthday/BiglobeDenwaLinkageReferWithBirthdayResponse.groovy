package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.referwithbirthday

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.ValidBiglobeDenwaLinkage
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.policy.BiglobeDenwaBirthDayCheckStatus
import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BiglobeDenwaLinkageReferWithBirthdayResponse {

    @Autowired
    private JsonTemplate template;


    public Map build(BiglobeDenwaBirthDayCheckStatus biglobeDenwaBirthDayCheckStatus,BiglobeDenwaLinkage biglobeDenwaLinkage) {
        final String VOICE_STATUS = "voiceStatus";
        final String LINKAGE_STATUS = "linkageStatus";

        if (biglobeDenwaBirthDayCheckStatus.equals(BiglobeDenwaBirthDayCheckStatus.UNREGISTERED_LTE3G)) {
            return template.build(
                    [
                            (VOICE_STATUS)  : StereotypedCharacters.NOT_EXIST,
                            (LINKAGE_STATUS): StereotypedCharacters.NOT_EXIST
                    ]
            )
        }

        if (biglobeDenwaBirthDayCheckStatus.equals(BiglobeDenwaBirthDayCheckStatus.UNREGISTERED_DENWA)) {
            return template.build(
                    [
                            (VOICE_STATUS)  : StereotypedCharacters.EXIST,
                            (LINKAGE_STATUS): StereotypedCharacters.NOT_EXIST
                    ]
            )
        }

        ValidBiglobeDenwaLinkage validBiglobeDenwaLinkage =
                (ValidBiglobeDenwaLinkage) biglobeDenwaLinkage;


        return template.build(
                [
                        (VOICE_STATUS)                    : StereotypedCharacters.EXIST,
                        (LINKAGE_STATUS)                  : StereotypedCharacters.EXIST,
                        "biglobeDenwaEngagementStatus"    : validBiglobeDenwaLinkage.getBiglobeDenwaLinkageStatus().getApiValue(),
                        "biglobeDenwaNotification"        : validBiglobeDenwaLinkage.getBiglobeDenwaNotification().getApiValue(),
                        "biglobeDenwaStatusChangeDateTime": validBiglobeDenwaLinkage.getBiglobeDenwaStatusChangeDateTime().getApiValue()
                ]
        )
    }

}
