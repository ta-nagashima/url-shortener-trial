package jp.co.biglobe.isp.sample.user.datasource.authentication;

import jp.co.biglobe.isp.sample.user.domain.authentication.AuthenticatedUserId;
import jp.co.biglobe.isp.sample.user.domain.authentication.AuthenticationRepository;
import jp.co.biglobe.isp.sample.user.domain.authentication.SessionId;
import jp.co.biglobe.test.util.usecase.BobioUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class AuthenticationRepositoryTest {

    @Autowired
    private AuthenticationRepository sut;

    private static final String SCENARIO_NAME = "C_authexp";

    private BobioUseCase bobioUseCase = new BobioUseCase(SCENARIO_NAME);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws IOException {
        bobioUseCase.unset();
    }

    @Test
    public void _DEFAULT() throws IOException {
        // テストケースの設定
        bobioUseCase.set("DEFAULT");

        // 実行
        AuthenticatedUserId actual = sut.authenticate(new SessionId("test_session_id"));

        // 評価
        assertThat(actual, is(new AuthenticatedUserId("abc12345")));
    }

    @Test(expected = RuntimeException.class)
    public void _ERROR() throws IOException {
        // テストケースの設定
        bobioUseCase.set("ERROR");

        // 実行
        sut.authenticate(new SessionId("invalid_session_id"));
    }

}