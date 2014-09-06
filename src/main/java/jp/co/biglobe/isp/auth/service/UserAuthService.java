package jp.co.biglobe.isp.auth.service;

import jp.co.biglobe.isp.auth.domain.user.UserAuthManagement;
import jp.co.biglobe.isp.auth.domain.user.UserAuthMethodType;
import jp.co.biglobe.isp.auth.domain.user.UserAuthRepository;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    public ValidUserAuth auth(UserAuthManagement userAuthManagement){

        UserAuthMethodType userAuthMethodType = userAuthManagement.judgesAuthMethodType();
        if(userAuthMethodType.equals(UserAuthMethodType.SESSION_ID)) {
            return userAuthRepository.authBySessionId(userAuthManagement.getSessionId());
        }

        return userAuthRepository.authBySessionId(userAuthManagement.getSessionId());
    }

}
