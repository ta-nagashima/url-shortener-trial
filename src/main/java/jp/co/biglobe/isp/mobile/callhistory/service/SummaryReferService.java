package jp.co.biglobe.isp.mobile.callhistory.service;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.container.CallHistorySummaryReferContainer;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryListRepository;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryReferService {

    @Autowired
    private BiglobeIdSummaryListRepository biglobeIdSummaryListRepository;

    @Autowired
    private MsisdnSummaryListRepository msisdnSummaryListRepository;

    public CallHistorySummaryReferContainer refer(UserId userId, TargetMonth targetMonth){

        BiglobeIdSummaryList biglobeIdSummaryList
                = biglobeIdSummaryListRepository.findByUserIdAndTargetMonth(userId, targetMonth);

        MsisdnSummaryList msisdnSummaryList
                = msisdnSummaryListRepository.findByUserIdAndTargetMonth(userId, targetMonth);

        return new CallHistorySummaryReferContainer(targetMonth, biglobeIdSummaryList, msisdnSummaryList);
    }



}
