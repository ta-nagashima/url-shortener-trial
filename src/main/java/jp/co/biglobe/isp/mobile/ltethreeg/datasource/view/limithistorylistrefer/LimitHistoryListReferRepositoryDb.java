package jp.co.biglobe.isp.mobile.ltethreeg.datasource.view.limithistorylistrefer;

import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.limithistory.db.LimitHistoryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LimitHistoryListReferRepositoryDb implements LimitHistoryListReferRepository {

    @Autowired
    private LimitHistoryMapper limitHistoryMapper;

    @Override
    public LimitHistoryTotalCount findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        return limitHistoryMapper.findByLteThreeGEngagementNumberForCount(lteThreeGEngagementNumber);

    }

    @Override
    public LimitHistoryList findAllByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber, Paging paging){

        Integer row = paging.getLow();
        Integer high = paging.getHigh();

       List<LimitHistoryForView> list = limitHistoryMapper.findAllByLteThreeGEngagementNumber(lteThreeGEngagementNumber, row, high);

        return new LimitHistoryList(list);

    }
}
