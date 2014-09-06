package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimList;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthEntityListFactory {

    public LteThreeGConnectAuthEntityList create(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity,
                                                 ValidSimList validSimList,
                                                 ConnectControlPolicy connectControlPolicy,
                                                 VpnEngagementEntity vpnEngagementEntity,
                                                 ValidBiglobeMember validBiglobeMember) {

        List<LteThreeGConnectAuthUserEntity> list = new ArrayList();
        LteThreeGConnectAuthEntityFactory lteThreeGConnectAuthEntityFactory = new LteThreeGConnectAuthEntityFactory();

        for (ValidSimEntity validSimEntity : validSimList.getValue()) {
            if (validSimEntity.isMsisdnConnectSet()) {
                list.add(lteThreeGConnectAuthEntityFactory.createForUser(
                    validLteThreeGEngagementEntity,
                    (ValidMsisdn) validSimEntity.getMsisdn(),
                    connectControlPolicy,
                    vpnEngagementEntity,
                    validBiglobeMember)

                );
            }
        }

        return new LteThreeGConnectAuthEntityList(list);

    }
}
