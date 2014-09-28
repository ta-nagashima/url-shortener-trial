package jp.co.biglobe.isp.sample.user.datasource.authentication.scenario;


import jp.co.biglobe.isp.sample.user.domain.authentication.AuthenticatedUserId;
import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUserName;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class UserAuthenticationOutput {
    @Getter
    private BobioHeader bobioHeader;

    @Mapping("hi_sosaid_out")
    @Getter
    private AuthenticatedUserId authenticatedUserId;

    @Mapping("simei_kanji")
    @Getter
    private SampleUserName sampleUserName;
}
