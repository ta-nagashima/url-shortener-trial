package jp.co.biglobe.isp.auth.datasource.operatornouser.scenario.sessionid;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class OperatorAuthBySessionIdOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("tantoid_out")
    @Getter
    private OperatorId operatorId;

    @Mapping("tanto_simei_kanji")
    @Getter
    private FullName name;

}
