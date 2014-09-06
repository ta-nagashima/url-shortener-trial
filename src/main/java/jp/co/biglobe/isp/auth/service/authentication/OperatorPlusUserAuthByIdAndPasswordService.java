package jp.co.biglobe.isp.auth.service.authentication;

import jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbyidandpassword.OperatorPlusUserAuthByIdAndPasswordRepository;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorPlusUserAuthByIdAndPasswordService {

    @Autowired
    private OperatorPlusUserAuthByIdAndPasswordRepository operatorPlusUserAuthByIdAndPasswordRepository;

    public ValidUserAuthTemp auth(OperatorId operatorId, OperatorPassword operatorPassword, UserId userId){

        return operatorPlusUserAuthByIdAndPasswordRepository.auth(operatorId, operatorPassword, userId);

    }


}
