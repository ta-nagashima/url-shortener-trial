package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer;

import jp.co.biglobe.isp.mobile.extension.paging.Paging;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import org.springframework.data.domain.Pageable;

public interface ChargeEngagementListReferRepository {

    public ChargeEngagementEntityTotalCount findByLteThreeGEngagementNumberForCount(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ChargeEngagementEntityList findAllByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber, Paging paging);
}
