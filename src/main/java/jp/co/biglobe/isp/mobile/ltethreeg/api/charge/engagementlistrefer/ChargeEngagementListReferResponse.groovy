package jp.co.biglobe.isp.mobile.ltethreeg.api.charge.engagementlistrefer

import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntity
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntityList
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer.ChargeEngagementEntityTotalCount
import jp.co.biglobe.isp.mobile.ltethreeg.service.charge.ChargeEngagementListReferService.ChargeEngagementListReferContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ChargeEngagementListReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(ChargeEngagementListReferContainer chargeEngagementListReferContainer) {


        ChargeEngagementEntityTotalCount chargeEngagementEntityTotalCount = chargeEngagementListReferContainer.getChargeEngagementEntityTotalCount();
        ChargeEngagementEntityList chargeEngagementEntityList = chargeEngagementListReferContainer.getChargeEngagementEntityList();

        return template.build(
                [
                        "totalCount": chargeEngagementEntityTotalCount.getApiValue(),
                ]
                        + buildChargeEngagementList(chargeEngagementListReferContainer, chargeEngagementEntityList)
        );

    }

    private Map buildChargeEngagementList(ChargeEngagementListReferContainer chargeEngagementListReferContainer, ChargeEngagementEntityList chargeEngagementEntityList) {

        List<ChargeEngagementEntity> list = chargeEngagementEntityList.getList()

        Integer getCount = list.size();
        if (getCount == 0) {
            return [
                    "getCount" : getCount.toString()
            ]
        }

        return [
                "getCount" : getCount.toString(),
                "chargeEngagementList" : list.collect { ChargeEngagementEntity chargeEngagementEntity ->
                    (buildChargeEngagement(chargeEngagementListReferContainer, chargeEngagementEntity))
                }
        ]

    }

    private Map buildChargeEngagement(ChargeEngagementListReferContainer chargeEngagementListReferContainer, ChargeEngagementEntity chargeEngagementEntity) {

        return [
                "optionJapaneseName"      : chargeEngagementEntity.getOptionJapaneseName(),
                "chargeEngagementNumber"  : chargeEngagementEntity.getChargeEngagementNumber(),
                "purchasedVolumeMB"       : chargeEngagementEntity.getPurchasedVolumeMB().toString(),
                "chargeCompletionStatus"  : chargeEngagementListReferContainer.getCurrentStatus(chargeEngagementEntity.getId()),
                "chargeOrderDateTime"     : chargeEngagementEntity.getChargeOrderDateTime(),
                "chargeApplicationEndDateTime" : chargeEngagementEntity.getChargeApplicationEnd().getChargeApplicationEndDateTimeForApiValue()
        ]

    }

}
