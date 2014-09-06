package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;

public interface ConnectAuthRepository {

    public LteThreeGConnectAuthChargeFreeEntity createForChargeFree(ValidMsisdn validMsisdn);

    public void update(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity);

    public void contract(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity);

    public void changeChargeFreeToUser(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity);

    public void changeUserToChargeFree(UserId userId, LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity);

}
