package jp.co.biglobe.isp.auth.domain.auth.operatorplususerauthbyidandpassword;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;

public interface OperatorPlusUserAuthByIdAndPasswordRepository {

    public ValidUserAuthTemp auth(OperatorId operatorId, OperatorPassword operatorPassword, UserId userId);
}
