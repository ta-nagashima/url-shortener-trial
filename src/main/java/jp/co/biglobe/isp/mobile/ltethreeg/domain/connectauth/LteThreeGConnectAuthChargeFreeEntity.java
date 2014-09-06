package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.ValidCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.BandControlDevice;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.connecttype.ConnectType;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.NotExistVpnCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.ValidVpnCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.VpnCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.VpnCorporationFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthChargeFreeEntity implements LteThreeGConnectAuthEntity {

    @Getter
    private final ValidMsisdn validMsisdn;

    @Getter
    private final AfterBuyChargeFreeId afterBuyChargeFreeId;

    @Getter
    private final BandControlDevice bandControlDevice;

    @Getter
    private final ConnectType connectType;


    public LteThreeGConnectAuthUserEntity createChangedId(
            ValidLteThreeGEngagementEntity validLteThreeGEngagementEntity,
            VpnEngagementEntity vpnEngagementEntity,
            ValidBiglobeMember validBiglobeMember) {


        return new LteThreeGConnectAuthUserEntity(
                validMsisdn,
                validLteThreeGEngagementEntity.getUserId(),
                validLteThreeGEngagementEntity.getLteThreeGEngagementNumber(),
                bandControlDevice,
//                connectType,
                new ConnectType(validLteThreeGEngagementEntity.getLteThreeGServicePlan()),
                VpnCorporationFactory.create(vpnEngagementEntity, validBiglobeMember)
        );
    }

    @Override
    public InternationalMsisdn getInternationalMsisdn() {
        return validMsisdn.getInternationalMsisdn();
    }


}
