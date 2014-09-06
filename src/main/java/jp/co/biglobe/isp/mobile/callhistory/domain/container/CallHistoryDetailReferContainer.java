package jp.co.biglobe.isp.mobile.callhistory.domain.container;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.History;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.HistoryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class CallHistoryDetailReferContainer {

    private final MsisdnSummaryList msisdnSummaryList;

    private final HistoryList historyList;

    public String getCallAndTransCount(CallKind callKind, ValidMsisdn msisdn) {

        return msisdnSummaryList.getMsisdnSummary(callKind, msisdn)
                .map(s -> s.getSumCountTransApiValueForDetail()).orElse("0");
    }

    public List<History> getHistoryList(){
        return historyList.getList();
    }

}
