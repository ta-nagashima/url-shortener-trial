package jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryList
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.BiglobeIdSummary
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.MsisdnSummary
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallHistorySummaryReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(TargetMonth targetMonth, BiglobeIdSummaryList biglobeIdSummaryList, MsisdnSummaryList msisdnSummaryList) {

        Map commonMap = [ "targetMonth": targetMonth.getValue() ]

        Map map = [
                "domestic": [
                        "parent": [
                                "engagement": biglobeIdSummaryList.getList(CallKind.DOMESTIC).collect { buildBiglobeIdSummary(it) },
                                "msisdn": msisdnSummaryList.getList(CallKind.DOMESTIC).collect { buildMsisdnummary(it) }
                        ]
                ],
                "international": [
                        "parent": [
                                "engagement": biglobeIdSummaryList.getList(CallKind.INTERNATIONAL).collect { buildBiglobeIdSummary(it) },
                                "msisdn": msisdnSummaryList.getList(CallKind.INTERNATIONAL).collect { buildMsisdnummary(it) }
                        ]
                ]

        ]


        return template.build(commonMap + map);
    }

    private Map buildBiglobeIdSummary(BiglobeIdSummary biglobeIdSummary) {

        return [
                "chargeName": biglobeIdSummary.getChargeNameApiValue(),
                "engagementNumber" : biglobeIdSummary.getLteThreeGEngagementNumberApiValue(),
                "unitPrice" : biglobeIdSummary.getUnitPriceApiValue(),
                "total"     : biglobeIdSummary.getSumChargeAmountApiValue()
        ]
    }

    private Map buildMsisdnummary(MsisdnSummary msisdnSummary){

        return [
                "msisdn": msisdnSummary.getMsisdnApiValue(),
                "total" : msisdnSummary.getSumChargeAmountApiValue()
        ]
    }

}
