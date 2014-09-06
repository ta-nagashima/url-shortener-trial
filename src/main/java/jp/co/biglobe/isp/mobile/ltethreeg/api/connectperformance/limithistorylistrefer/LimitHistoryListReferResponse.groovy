package jp.co.biglobe.isp.mobile.ltethreeg.api.connectperformance.limithistorylistrefer

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistory
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.LimitHistoryForView
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.LimitHistoryList
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.LimitHistoryTotalCount
import jp.co.biglobe.isp.mobile.ltethreeg.service.LimitHistoryListReferService.LimitHistoryListReferContainer
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LimitHistoryListReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(LimitHistoryListReferContainer limitHistoryListReferContainer) {


        LimitHistoryTotalCount limitHistoryTotalCount = limitHistoryListReferContainer.getLimitHistoryTotalCount()
        LimitHistoryList limitHistoryList = limitHistoryListReferContainer.getLimitHistoryList()

        return template.build(
                [
                        "totalCount": limitHistoryTotalCount.getApiValue(),
                ]
                        + buildLimitHistoryList(limitHistoryList)
        );
    }

    public Map buildLimitHistoryList(LimitHistoryList limitHistoryList) {

        List<LimitHistoryForView> list = limitHistoryList.getList()

        Integer getCount = list.size();
        if (getCount == 0) {
            return [
                    "getCount" : getCount.toString()
            ]
        }

        return [
                "getCount" : getCount.toString(),
                "limitHistoryList" : list.collect { LimitHistoryForView limitHistoryForView ->
                    (buildLimitHistory(limitHistoryForView.getLimitHistory()))
                }
        ]

    }

    public Map buildLimitHistory(LimitHistory limitHistory) {

        return [
                "beforeSpeedLimitStatus"      : limitHistory.getBeforeSpeedLimitStatus().getApiValue(),
                "afterSpeedLimitStatus"       : limitHistory.getAfterSpeedLimitStatus().getApiValue(),
                "beforeDestinationLimitStatus": limitHistory.getBeforeDestinationLimitStatus().getApiValue(),
                "afterDestinationLimitStatus" : limitHistory.getAfterDestinationLimitStatus().getApiValue(),
                "beforeConnectControlPolicy"  : limitHistory.getBeforeConnectControlPolicy().getApiValue(),
                "afterConnectControlPolicy"   : limitHistory.getAfterConnectControlPolicy().getApiValue(),
                "limitHistoryRequestDateTime" : limitHistory.getLimitHistoryRequestDateTime().getApiValue(),
                "connectPerformanceEvent"     : limitHistory.getConnectPerformanceEvent().getApiValue(),
        ]

    }

}
