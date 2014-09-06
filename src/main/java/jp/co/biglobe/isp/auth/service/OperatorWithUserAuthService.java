package jp.co.biglobe.isp.auth.service;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorAuthMethodType;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorWithUserAuthManagement;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuth;
import jp.co.biglobe.isp.auth.domain.operatorwithuser.OperatorWithUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorWithUserAuthService {

    @Autowired
    private OperatorWithUserAuthRepository operatorWithUserAuthRepository;

    public OperatorWithUserAuth auth(OperatorWithUserAuthManagement operatorWithUserAuthManagement) {


        OperatorAuthMethodType operatorAuthMethodType = operatorWithUserAuthManagement.judgesAuthMethodType();

        if (operatorAuthMethodType.equals(OperatorAuthMethodType.SESSION_ID_SSO)) {
            return operatorWithUserAuthRepository.authBySessionIdSso(operatorWithUserAuthManagement.getSessionId());
        }

        return operatorWithUserAuthRepository.authBySessionIdNoSso(
                operatorWithUserAuthManagement.getSessionId(),
                operatorWithUserAuthManagement.getOperatorId(),
                operatorWithUserAuthManagement.getUserId());
    }

}
