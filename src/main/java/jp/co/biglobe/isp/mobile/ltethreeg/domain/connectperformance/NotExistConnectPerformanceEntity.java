package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicyEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.LimitStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.speedstatus.SpeedStatus;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistConnectPerformanceEntity implements ConnectPerformanceEntity {

    @Override
    public boolean isNotExist() {
        return true;
    }

}
