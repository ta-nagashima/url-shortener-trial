package jp.co.biglobe.isp.auth.service.authentication;

import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.SessionIdAuth;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.SessionIdAuthResult;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.UserAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthBySessionIdService {

    @Autowired
    private UserAuthBySessionIdRepository userAuthBySessionIdRepository;


    public SessionIdAuthResult auth(SessionId sessionId){

        SessionIdAuth sessionIdAuth = userAuthBySessionIdRepository.auth(sessionId);

        return sessionIdAuth.verify();

    }


}
