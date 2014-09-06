package jp.co.biglobe.isp.mobile.ltethreeg.service;

import jp.co.biglobe.isp.mobile.biglobemember.domain.BiglobeMemberRepository;
import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.ConnectAuthRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthChargeFreeEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.LteThreeGConnectAuthUserEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEntityFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementRepository;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectAuthChangeService {

    @Autowired
    private ConnectAuthRepository connectAuthRepository;

    @Autowired
    private LteThreeGEngagementRepository lteThreeGEngagementRepository;

    @Autowired
    private VpnEngagementRepository vpnEngagementRepository;

    @Autowired
    private ConnectPerformanceRepository connectPerformanceRepository;

    @Autowired
    private BiglobeMemberRepository biglobeMemberRepository;


    public void changeChargeFreeToUser(ValidMsisdn validMsisdn, LteThreeGEngagementNumber lteThreeGEngagementNumber){
        //LTE契約の参照
        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity =
                lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValidForEnable(lteThreeGEngagementNumber);


        //認証情報の登録
        lteThreeGConnectAuthRegister(validMsisdn,validLteThreeGEngagementEntity);

        //通信実績関連、履歴の登録
        connectPerformanceRegister(validLteThreeGEngagementEntity);

    }

    private void lteThreeGConnectAuthRegister(ValidMsisdn validMsisdn,ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        //認証エンティティの作成（商品契約の参照）
        LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity = connectAuthRepository.createForChargeFree(validMsisdn);

        ValidBiglobeMember validBiglobeMember = biglobeMemberRepository.findByUserIdNoCafeForValid(validLteThreeGEngagementEntity.getUserId());

        VpnEngagementEntity vpnEngagementEntity = vpnEngagementRepository.findByLteThreeGEngagementNumber(validLteThreeGEngagementEntity.getLteThreeGEngagementNumber());

        //ユーザIDでの認証エンティティの作成
        LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity
                = lteThreeGConnectAuthChargeFreeEntity.createChangedId(validLteThreeGEngagementEntity, vpnEngagementEntity, validBiglobeMember);

        //複写！（無償IDの認証解除・ユーザIDでの認証設定
        connectAuthRepository.changeChargeFreeToUser(lteThreeGConnectAuthUserEntity);

    }

    private void connectPerformanceRegister(ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity){
        ConnectPerformanceEntityFactory connectPerformanceEntityFactory = new ConnectPerformanceEntityFactory();
        ValidConnectPerformanceEntity validConnectPerformanceEntity = connectPerformanceEntityFactory.createDestinationLimited(validLteThreeGEngagementEntity);
        connectPerformanceRepository.insert(validConnectPerformanceEntity);
    }


    public void changeUserToChargeFree(ValidMsisdn validMsisdn, LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity =
                lteThreeGEngagementRepository.findByLteThreeGEngagementNumberForValid(lteThreeGEngagementNumber);


        LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity = connectAuthRepository.createForChargeFree(validMsisdn);

        connectAuthRepository.changeUserToChargeFree(validLteThreeGEngagementEntity.getUserId(), lteThreeGConnectAuthChargeFreeEntity);
    }

}
