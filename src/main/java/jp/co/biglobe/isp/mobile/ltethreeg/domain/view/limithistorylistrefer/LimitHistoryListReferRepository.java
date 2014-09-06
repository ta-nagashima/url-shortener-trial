package jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer;

import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;

public interface LimitHistoryListReferRepository {

    public LimitHistoryTotalCount findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public LimitHistoryList findAllByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber, Paging paging);

}
