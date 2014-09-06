package jp.co.biglobe.isp.mobile.callhistory.datasource.summary.msisdnsummary;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.datasource.summary.msisdnsummary.db.MsisdnSummaryQueryMapper;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn.MsisdnSummaryListRepository;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.MsisdnSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MsisdnSummaryListRepositoryDb implements MsisdnSummaryListRepository{

    @Autowired
    private MsisdnSummaryQueryMapper msisdnSummaryQueryMapper;


    @Override
    public MsisdnSummaryList findByUserIdAndTargetMonth(UserId userId, TargetMonth targetMonth) {

        List<MsisdnSummary> msisdnSummaryList
                = msisdnSummaryQueryMapper.findByUserIdAndTargetMonth(
                userId, targetMonth, targetMonth.getFirstDate(), targetMonth.getLastDate());

        return new MsisdnSummaryList(msisdnSummaryList);
    }
}
