package jp.co.biglobe.isp.mobile.callhistory.service;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.container.CallHistoryDetailReferContainer;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.HistoryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.HistoryListRepository;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.paging.Paging;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryListRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailReferService {

    @Autowired
    private MsisdnSummaryListRepository msisdnSummaryListRepository;

    @Autowired
    private HistoryListRepository historyListRepository;

    public CallHistoryDetailReferContainer refer(
            UserId userId, ValidMsisdn validMsisdn,
            TargetMonth targetMonth, Paging paging, CallKind callKind) {

        MsisdnSummaryList msisdnSummaryList
                = msisdnSummaryListRepository.findByUserIdAndTargetMonth(userId, targetMonth);

        HistoryList historyList
                = historyListRepository.findByUserIdAndMsisdnAndTargetMonth(
                userId, validMsisdn, targetMonth, paging, callKind
        );

        return new CallHistoryDetailReferContainer(msisdnSummaryList, historyList);
    }


}
