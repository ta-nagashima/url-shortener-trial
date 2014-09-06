package jp.co.biglobe.isp.auth.service.authentication.totalauth;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid.OperatorAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorAuthBySessionIdTotalAuthService implements TotalAuthServiceImpl {

    @Autowired
    private OperatorAuthBySessionIdRepository operatorAuthBySessionIdRepository;

    @Override
    public TotalAuthResult auth(TotalAuth totalAuth){
        return operatorAuthBySessionIdRepository.getBiglobeMember(totalAuth.getSessionId());
    }

}
