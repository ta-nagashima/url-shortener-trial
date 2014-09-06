package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitHistoryListReferService {

    @Autowired
    private LimitHistoryListReferRepository limitHistoryListReferRepository;

    public LimitHistoryListReferContainer refer(
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            Paging paging){

        LimitHistoryTotalCount limitHistoryTotalCount = limitHistoryListReferRepository.findByLteThreeGEngagementNumberForCount(lteThreeGEngagementNumber);

        LimitHistoryList limitHistoryList = limitHistoryListReferRepository.findAllByLteThreeGEngagementNumber(lteThreeGEngagementNumber, paging);

        return new LimitHistoryListReferContainer(limitHistoryTotalCount, limitHistoryList);
    }

    @AllArgsConstructor
    public class LimitHistoryListReferContainer {

        @Getter
        private final LimitHistoryTotalCount limitHistoryTotalCount;

        @Getter
        private final LimitHistoryList limitHistoryList;

    }

}
