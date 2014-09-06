package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.outsidelinkagerefer

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.ValidBiglobeDenwaLinkage
import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OutsideLinkageReferResponse {
    @Autowired
    private JsonTemplate template;

    public Map build(LteThreeGEngagementEntity lteThreeGEngagementEntity, BiglobeDenwaLinkage cooperationNumberWithOtherCompany) {
        final String VOICE_STATUS = "voiceStatus";
        final String LINKAGE_STATUS = "linkageStatus";

        if(lteThreeGEngagementEntity.isInvalid()) {
            return template.build(
                [
                    (VOICE_STATUS) : StereotypedCharacters.NOT_EXIST,
                    (LINKAGE_STATUS) : StereotypedCharacters.NOT_EXIST
                ]
            )
        }

        if(cooperationNumberWithOtherCompany.isNotExist()) {
            return template.build(
                [
                        (VOICE_STATUS) : StereotypedCharacters.EXIST,
                        (LINKAGE_STATUS) : StereotypedCharacters.NOT_EXIST
                ]
            )
        }

        ValidBiglobeDenwaLinkage validCooperationNumberWithOtherCompany =
                (ValidBiglobeDenwaLinkage)cooperationNumberWithOtherCompany;

        return template.build(
            [
                (VOICE_STATUS) : StereotypedCharacters.EXIST,
                (LINKAGE_STATUS) : StereotypedCharacters.EXIST,
                "biglobeDenwaEngagementStatus":validCooperationNumberWithOtherCompany.getBiglobeDenwaLinkageStatus().getApiValue(),
                "biglobeDenwaNotification":validCooperationNumberWithOtherCompany.getBiglobeDenwaNotification().getApiValue(),
                "biglobeDenwaStatusChangeDateTime":validCooperationNumberWithOtherCompany.getBiglobeDenwaStatusChangeDateTime().getApiValue()
            ]
        )

    }
}
