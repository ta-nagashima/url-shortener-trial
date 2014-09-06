package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMemberRepository;
import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.BeforeConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.SimRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidSimList;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAuthLocalService {

    @Autowired
    SimRepository simRepository;

    @Autowired
    ConnectAuthRepository connectAuthRepository;

    @Autowired
    private VpnEngagementRepository vpnEngagementRepository;

    @Autowired
    private BiglobeMemberRepository biglobeMemberRepository;

    public void updateAllMaybe(
            BeforeConnectPerformanceEntity beforeConnectPerformanceEntity,
            AfterConnectPerformanceEntity afterConnectPerformanceEntity,
            ValidLteThreeGEngagementEntity validLteThreeGEngagement) {

        if (afterConnectPerformanceEntity.isSameConnectControlPolicy(beforeConnectPerformanceEntity)) {
            return;
        }
        updateAll(afterConnectPerformanceEntity, validLteThreeGEngagement);
    }

    public void updateAll(
            AfterConnectPerformanceEntity afterConnectPerformanceEntity,
            ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity) {

        VpnEngagementEntity vpnEngagementEntity = vpnEngagementRepository.findByLteThreeGEngagementNumber(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());

        ValidBiglobeMember validBiglobeMember = biglobeMemberRepository.findByUserIdNoCafeForValid(validLteThreeGEngagementEntity.getUserId());

        ValidSimList validSimList = simRepository.findByLteThreeGEngagementNumberUnderValid(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());
        LteThreeGConnectAuthEntityList lteThreeGConnectAuthEntityList = new LteThreeGConnectAuthEntityListFactory().create(
                validLteThreeGEngagementEntity,
                validSimList,
                afterConnectPerformanceEntity.getConnectControlPolicyEntity().getConnectControlPolicy(),
                vpnEngagementEntity,
                validBiglobeMember);

        for (LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity : lteThreeGConnectAuthEntityList.getValue()) {
            connectAuthRepository.update(lteThreeGConnectAuthUserEntity);
        }

    }

    void contract(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity,
                  ValidConnectPerformanceEntity validConnectPerformanceEntity,
                  ValidMsisdn validMsisdn){

        VpnEngagementEntity vpnEngagementEntity = vpnEngagementRepository.findByLteThreeGEngagementNumber(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());

        ValidBiglobeMember validBiglobeMember = biglobeMemberRepository.findByUserIdNoCafeForValid(validLteThreeGEngagementEntity.getUserId());

        LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity = new LteThreeGConnectAuthEntityFactory().createForUser(
                validLteThreeGEngagementEntity,
                validMsisdn,
                validConnectPerformanceEntity.getConnectControlPolicyEntity().getConnectControlPolicy(),
                vpnEngagementEntity,
                validBiglobeMember);

        connectAuthRepository.contract(lteThreeGConnectAuthUserEntity);

    }
}
