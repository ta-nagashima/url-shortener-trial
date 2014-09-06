package jp.co.biglobe.isp.auth.datasource.operatorwithuser.scenario.sessionid;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class OperatorPlusUserAuthBySessionIdOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("tantoid_out")
    @Getter
    private OperatorId operatorId;

    @Mapping("tanto_simei_kanji")
    @Getter
    private FullName operatorFullName;


    @Mapping("hi_sosaid_out")
    @Getter
    private UserId userId;

    @Mapping("hi_sosaid_simei_kanji")
    @Getter
    private FullName userFullName;

}
