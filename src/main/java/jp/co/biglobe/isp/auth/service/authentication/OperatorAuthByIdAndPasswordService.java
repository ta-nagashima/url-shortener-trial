package jp.co.biglobe.isp.auth.service.authentication;

import jp.co.biglobe.isp.auth.domain.auth.operatorauthbyidandpassword.OperatorAuthByIdAndPasswordRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorAuthByIdAndPasswordService {

    @Autowired
    private OperatorAuthByIdAndPasswordRepository operatorAuthByIdAndPasswordRepository;

    public void auth(OperatorId operatorId, OperatorPassword operatorPassword) {

        operatorAuthByIdAndPasswordRepository.auth(operatorId, operatorPassword);

    }
}