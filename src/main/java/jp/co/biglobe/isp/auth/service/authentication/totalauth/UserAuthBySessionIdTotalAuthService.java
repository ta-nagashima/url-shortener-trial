package jp.co.biglobe.isp.auth.service.authentication.totalauth;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid.UserAuthBySessionIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthBySessionIdTotalAuthService implements TotalAuthServiceImpl {

    @Autowired
    private UserAuthBySessionIdRepository userAuthBySessionIdRepository;

    @Override
    public TotalAuthResult auth(TotalAuth totalAuth){
        return  userAuthBySessionIdRepository.auth2(totalAuth.getSessionId());
    }

}
