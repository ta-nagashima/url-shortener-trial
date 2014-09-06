package jp.co.biglobe.isp.auth.domain.auth.operatorauthbyidandpassword;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorPassword;

public interface OperatorAuthByIdAndPasswordRepository {

    public void auth(OperatorId operatorId, OperatorPassword operatorPassword);


}
