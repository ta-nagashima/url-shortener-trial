package jp.co.biglobe.isp.mobile.callhistory.domain.container;

import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class CallHistorySummaryReferContainer {

    @Getter
    private final TargetMonth targetMonth;

    @Getter
    private final BiglobeIdSummaryList biglobeIdSummaryList;

    @Getter
    private final MsisdnSummaryList msisdnSummaryList;


}
