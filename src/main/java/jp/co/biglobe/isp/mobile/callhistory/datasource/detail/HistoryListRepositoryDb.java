package jp.co.biglobe.isp.mobile.callhistory.datasource.detail;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.datasource.detail.db.HistoryListQueryMapper;
import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.History;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.HistoryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.detail.HistoryListRepository;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryListRepositoryDb implements HistoryListRepository {

    @Autowired
    private HistoryListQueryMapper historyListQueryMapper;


    @Override
    public HistoryList findByUserIdAndMsisdnAndTargetMonth(
            UserId userId, ValidMsisdn validMsisdn,
            TargetMonth targetMonth, Paging paging, CallKind callKind) {

        List<History> list = historyListQueryMapper.findByUserIdAndMsisdnAndTargetMonth(
                userId, validMsisdn, targetMonth, paging.getLow(), paging.getHigh(), callKind);

        return new HistoryList(list);
    }
}
