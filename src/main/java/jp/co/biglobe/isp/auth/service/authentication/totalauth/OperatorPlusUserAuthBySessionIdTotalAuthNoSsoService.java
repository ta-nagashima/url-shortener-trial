package jp.co.biglobe.isp.auth.service.authentication.totalauth;

import jp.co.biglobe.isp.auth.domain.auth.TotalAuth;
import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbysessionid.OperatorPlusUserAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorPlusUserAuthBySessionIdTotalAuthNoSsoService implements TotalAuthServiceImpl {

    @Autowired
    private OperatorPlusUserAuthBySessionIdRepository operatorPlusUserAuthBySessionIdRepository;

    @Override
    public TotalAuthResult auth(TotalAuth totalAuth){
        return operatorPlusUserAuthBySessionIdRepository.getBiglobeMember(
                totalAuth.getSessionId(),
                totalAuth.getOperatorId(),
                totalAuth.getUserId()
        );

    }


}
