package jp.co.biglobe.isp.auth.datasource.auth.operatorplususerauthbyidandpassword.scenario;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class OperatorAuthWithBiglobeIdOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("hi_sosaid_out")
    @Getter
    private UserId userId;

    @Mapping("hi_sosaid_simei_kanji")
    @Getter
    private FullName name;

}
