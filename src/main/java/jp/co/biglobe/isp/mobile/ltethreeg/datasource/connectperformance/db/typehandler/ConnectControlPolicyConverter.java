package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.db.typehandler;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlDestinationLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedLimitPolicy;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.connectcontrolpolicy.ConnectControlSpeedNoLimitPolicy;

import java.util.NoSuchElementException;

public class ConnectControlPolicyConverter {

    public static ConnectControlPolicy convertForDb(String dbValue) {
        ConnectControlDestinationLimitPolicy[] destinationLimitPolicies = ConnectControlDestinationLimitPolicy.values();
        for (ConnectControlDestinationLimitPolicy destinationLimitPolicy : destinationLimitPolicies) {
            if (destinationLimitPolicy.getDbValue().equals(dbValue)) {
                return destinationLimitPolicy;
            }
        }

        ConnectControlSpeedLimitPolicy[] speedLimitPolicies = ConnectControlSpeedLimitPolicy.values();
        for (ConnectControlSpeedLimitPolicy speedLimitPolicy : speedLimitPolicies) {
            if (speedLimitPolicy.getDbValue().equals(dbValue)) {
                return speedLimitPolicy;
            }
        }

        ConnectControlSpeedNoLimitPolicy[] noLimitPolicies = ConnectControlSpeedNoLimitPolicy.values();
        for (ConnectControlSpeedNoLimitPolicy noLimitPolicy : noLimitPolicies) {
            if (noLimitPolicy.getDbValue().equals(dbValue)) {
                return noLimitPolicy;
            }
        }

        throw new NoSuchElementException("ConnectControlPolicyを継承しているenumクラスをConnectControlPolicyConverterに追加してください。");

    }

}