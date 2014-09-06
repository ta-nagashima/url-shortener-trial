package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.bandcontroldevice.SessionBandControlDeviceScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthcontract.ConnectAuthContractScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.ConnectAuthDeleteScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer.ConnectAuthReferOutput;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer.ConnectAuthReferScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthupdate.ConnectAuthUpdateScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.ApplicationDestinationPolicyId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectAuthRepositoryScenario implements ConnectAuthRepository {

    @Autowired
    private ConnectAuthUpdateScenario connectAuthUpdateScenario;

    @Autowired
    private ConnectAuthReferScenario connectAuthReferScenario;

    @Autowired
    private ConnectAuthDeleteScenario connectAuthDeleteScenario;

    @Autowired
    private ConnectAuthContractScenario connectAuthContractScenario;

    @Autowired
    private PropertyAccessor propertyAccessor;

    @Autowired
    private SessionBandControlDeviceScenario sessionBandControlDeviceScenario;

    @Override
    public LteThreeGConnectAuthChargeFreeEntity createForChargeFree(ValidMsisdn validMsisdn) {
        AfterBuyChargeFreeId afterBuyChargeFreeId = getAfterBuyChargeFreeId();

        return LteThreeGConnectAuthEntityFactory.createForChargeFree(validMsisdn, afterBuyChargeFreeId);
    }

    @Override
    public void update(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity) {
        updateParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEA);
        updateParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEB);
        updateParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.B3G);
        sessionBandControlDeviceScenario.update(lteThreeGConnectAuthUserEntity.getInternationalMsisdn(), lteThreeGConnectAuthUserEntity.getConnectControlPolicy());
    }

    private void updateParts(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity,
                             ApplicationDestinationPolicyId applicationDestinationPolicyId) {

        ConnectAuthReferOutput connectAuthReferOutput = connectAuthReferScenario.referForUser(
                lteThreeGConnectAuthUserEntity.getUserId(),
                applicationDestinationPolicyId,
                lteThreeGConnectAuthUserEntity.getInternationalMsisdn());

        connectAuthUpdateScenario.update(
                lteThreeGConnectAuthUserEntity, connectAuthReferOutput.getRiyoguchiNo(), connectAuthReferOutput.getKeiyakuEdano());
    }

    @Override
    public void contract(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity) {

        connectAuthContractScenario.contract(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEA);
        connectAuthContractScenario.contract(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEB);
        connectAuthContractScenario.contract(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.B3G);
    }


    @Override
    public void changeChargeFreeToUser(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity) {

        changeChargeFreeToUserParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEA);
        changeChargeFreeToUserParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.LTEB);
        changeChargeFreeToUserParts(lteThreeGConnectAuthUserEntity, ApplicationDestinationPolicyId.B3G);

        sessionBandControlDeviceScenario.update(lteThreeGConnectAuthUserEntity.getInternationalMsisdn(), lteThreeGConnectAuthUserEntity.getConnectControlPolicy());

    }

    private void changeChargeFreeToUserParts(LteThreeGConnectAuthUserEntity lteThreeGConnectAuthUserEntity,
                                             ApplicationDestinationPolicyId applicationDestinationPolicyId) {

        //無償IDに紐づくRadiusを削除（商品参照、商品契約更新）
        AfterBuyChargeFreeId afterBuyChargeFreeId = getAfterBuyChargeFreeId();
        ConnectAuthReferOutput connectAuthReferOutput = connectAuthReferScenario.referForChargeFree
                (lteThreeGConnectAuthUserEntity.getValidMsisdn(), afterBuyChargeFreeId, applicationDestinationPolicyId);

        connectAuthDeleteScenario.deleteForUser(lteThreeGConnectAuthUserEntity.getUserId(),
                connectAuthReferOutput.getRiyoguchiNo(), connectAuthReferOutput.getKeiyakuEdano());

        connectAuthContractScenario.contract(lteThreeGConnectAuthUserEntity, applicationDestinationPolicyId);

    }

    @Override
    public void changeUserToChargeFree(UserId userId, LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity) {

        changeUserToChargeFreeParts(userId, lteThreeGConnectAuthChargeFreeEntity, ApplicationDestinationPolicyId.LTEA);
        changeUserToChargeFreeParts(userId, lteThreeGConnectAuthChargeFreeEntity, ApplicationDestinationPolicyId.LTEB);
        changeUserToChargeFreeParts(userId, lteThreeGConnectAuthChargeFreeEntity, ApplicationDestinationPolicyId.B3G);
    }

    private void changeUserToChargeFreeParts(
            UserId userId,
            LteThreeGConnectAuthChargeFreeEntity lteThreeGConnectAuthChargeFreeEntity,
            ApplicationDestinationPolicyId applicationDestinationPolicyId) {

        ConnectAuthReferOutput connectAuthReferOutput = connectAuthReferScenario.referForUser(
                userId,
                applicationDestinationPolicyId,
                lteThreeGConnectAuthChargeFreeEntity.getInternationalMsisdn());


        connectAuthDeleteScenario.deleteForChargeFree(getAfterBuyChargeFreeId(),
                connectAuthReferOutput.getRiyoguchiNo(), connectAuthReferOutput.getKeiyakuEdano());

        connectAuthContractScenario.contractForChargeFree(lteThreeGConnectAuthChargeFreeEntity, applicationDestinationPolicyId);

        sessionBandControlDeviceScenario.update(lteThreeGConnectAuthChargeFreeEntity.getInternationalMsisdn(), lteThreeGConnectAuthChargeFreeEntity.getBandControlDevice().getConnectControlPolicy());
    }

    private AfterBuyChargeFreeId getAfterBuyChargeFreeId() {
        return new AfterBuyChargeFreeId(propertyAccessor.getProperty("after.buy.charge.free.id"));
    }
}
