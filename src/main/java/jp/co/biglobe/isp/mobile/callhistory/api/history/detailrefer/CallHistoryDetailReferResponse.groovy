package jp.co.biglobe.isp.mobile.callhistory.api.history.detailrefer

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind
import jp.co.biglobe.isp.mobile.callhistory.domain.container.CallHistoryDetailReferContainer
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.History
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallHistoryDetailReferResponse {

    @Autowired
    private JsonTemplate template;

    public Map build(CallHistoryDetailReferContainer callHistoryDetailReferContainer, CallKind internationalCallKind, ValidMsisdn msisdn) {

        def histList = callHistoryDetailReferContainer.getHistoryList();

        return template.build(
                [
                        "totalCount": callHistoryDetailReferContainer.getCallAndTransCount(internationalCallKind, msisdn),
                        "history"   : histList.collect {
                            buildHistory(it)
                        }
                ]
        )
    }

    private Map buildHistory(History history) {
        return [

                "taxStatus"          : history.getTaxStatusApiValue(),
                "startDateTime"      : history.getStartDateTimeApiValue(),
                "numberDialed"       : history.getDialedMsisdnApiValue(),
                "communicationMethod": history.getCommunicationMethodApiValue(),
                "chargeItemName"     : history.getChargeItemNameApiValue(),
                "duration"           : history.getDurationApiValue(),
                "charge"             : history.getChargeAmountApiValue(),
                "connectCountry"     : history.getConnectCountryForApiValue()
        ]
    }
}

