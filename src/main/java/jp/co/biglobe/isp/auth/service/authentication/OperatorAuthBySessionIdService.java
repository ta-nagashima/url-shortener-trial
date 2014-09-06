package jp.co.biglobe.isp.auth.service.authentication;

import jp.co.biglobe.isp.auth.domain.auth.operatorauthbysessionid.OperatorAuthBySessionIdRepository;
import jp.co.biglobe.isp.auth.domain.user.SessionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorAuthBySessionIdService {

    @Autowired
    private OperatorAuthBySessionIdRepository operatorAuthBySessionIdRepository;

    public void auth(SessionId sessionId){

        operatorAuthBySessionIdRepository.auth(sessionId);

        return;

    }
}
