package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.BandControlDevice;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.connecttype.ConnectType;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation.VpnCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class LteThreeGConnectAuthUserEntity implements LteThreeGConnectAuthEntity{

    @Getter
    private final ValidMsisdn validMsisdn;

    @Getter
    private final UserId userId;

    @Getter
    private final LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Getter
    private final BandControlDevice bandControlDevice;

    @Getter
    private final ConnectType connectType;

    @Getter
    private final VpnCorporation vpnCorporation;

    public boolean isVpnCorporationExist(){
        return vpnCorporation.isExist();
    }

    public ConnectControlPolicy getConnectControlPolicy() {
        return bandControlDevice.getConnectControlPolicy();
    }

    @Override
    public InternationalMsisdn getInternationalMsisdn() {
        return validMsisdn.getInternationalMsisdn();
    }

}
