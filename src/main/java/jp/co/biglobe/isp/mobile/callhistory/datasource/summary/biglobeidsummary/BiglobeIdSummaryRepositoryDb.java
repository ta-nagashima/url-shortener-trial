package jp.co.biglobe.isp.mobile.callhistory.datasource.summary.biglobeidsummary;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.callhistory.datasource.summary.biglobeidsummary.db.BiglobeIdSummaryQueryMapper;
import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryList;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid.BiglobeIdSummaryListRepository;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.BiglobeIdSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BiglobeIdSummaryRepositoryDb implements BiglobeIdSummaryListRepository {

    @Autowired
    private BiglobeIdSummaryQueryMapper biglobeIdSummaryQueryMapper;


    @Override
    public BiglobeIdSummaryList findByUserIdAndTargetMonth(UserId userId, TargetMonth targetMonth) {

        List<BiglobeIdSummary> biglobeIdSummaryList
                = biglobeIdSummaryQueryMapper.findByUserIdAndTargetMonth(
                userId, targetMonth, targetMonth.getFirstDate(), targetMonth.getLastDate());

        return new BiglobeIdSummaryList(biglobeIdSummaryList);
    }
}
