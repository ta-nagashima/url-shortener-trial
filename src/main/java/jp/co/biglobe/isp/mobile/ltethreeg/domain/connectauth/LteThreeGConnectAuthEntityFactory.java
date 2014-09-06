package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.BandControlDevice;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.connecttype.ConnectType;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.VpnCorporationFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlDestinationLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthEntityFactory {

    public LteThreeGConnectAuthUserEntity createForUser(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity,
                                              ValidMsisdn validMsisdn,
                                              ConnectControlPolicy connectControlPolicy,
                                              VpnEngagementEntity vpnEngagementEntity,
                                              ValidBiglobeMember validBiglobeMember){

        return new LteThreeGConnectAuthUserEntity(
                validMsisdn,
                validLteThreeGEngagementEntity.getUserId(),
                validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(),
                new BandControlDevice(connectControlPolicy),
                new ConnectType(validLteThreeGEngagementEntity.getLteThreeGServicePlan()),
                VpnCorporationFactory.create(vpnEngagementEntity, validBiglobeMember)
        );
    }

    public static LteThreeGConnectAuthChargeFreeEntity createForChargeFree(ValidMsisdn validMsisdn, AfterBuyChargeFreeId afterBuyChargeFreeId){

        return new LteThreeGConnectAuthChargeFreeEntity(
                validMsisdn,
                afterBuyChargeFreeId,
                new BandControlDevice(ConnectControlDestinationLimitPolicy.BIGLOBE_ONLY_LIMIT),
                new ConnectType(LteThreeGServicePlanFactory.createForAfterOnly(LteThreeGServicePlanCode.SERVICE_BEFORE_START))
        );

    }
}
