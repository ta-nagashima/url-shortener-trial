package jp.co.biglobe.isp.auth.service;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorAuthMethodType;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorNoUserAuthManagement;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorNoUserAuthRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.ValidOperatorNoUserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorNoUserAuthService {

    @Autowired
    private OperatorNoUserAuthRepository operatorNoUserAuthRepository;

    public ValidOperatorNoUserAuth auth(OperatorNoUserAuthManagement operatorNoUserAuthManagement) {


        OperatorAuthMethodType operatorAuthMethodType = operatorNoUserAuthManagement.judgesAuthMethodType();

        if (operatorAuthMethodType.equals(OperatorAuthMethodType.SESSION_ID_SSO)) {
            return operatorNoUserAuthRepository.authBySessionIdSsoNoUser(operatorNoUserAuthManagement.getSessionId());
        }

        return operatorNoUserAuthRepository.authBySessionIdNoSsoNoUser(
                operatorNoUserAuthManagement.getSessionId(), operatorNoUserAuthManagement.getOperatorId());


    }

}
