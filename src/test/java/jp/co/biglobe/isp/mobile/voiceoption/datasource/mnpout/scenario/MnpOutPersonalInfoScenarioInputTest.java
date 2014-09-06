package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.danger.scenario.bobio.BobioOutputData;
import jp.co.biglobe.lib.danger.scenario.converter.Property2BobioConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class MnpOutPersonalInfoScenarioInputTest {

    @Test
    public void INパラ確認() throws Exception{

        UserId userId = new UserId("abc12345");

        // 実行
        MnpOutPersonalInfoScenarioInput sut = new MnpOutPersonalInfoScenarioInput(userId);
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    private BobioOutputData createBobioOutputData(){

        BobioOutputData expected = new BobioOutputData();
        expected.put("sosaid", "SYSTEM");
        expected.put("hi_sosaid", "abc12345");

        return expected;

    }

}
