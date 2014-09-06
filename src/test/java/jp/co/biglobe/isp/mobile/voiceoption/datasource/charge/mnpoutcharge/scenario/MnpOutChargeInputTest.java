package jp.co.biglobe.isp.mobile.voiceoption.datasource.charge.mnpoutcharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutFee;
import jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge.MnpOutItemCode;
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
public class MnpOutChargeInputTest {

    @Test
    public void INパラ確認() throws Exception{

        OperatorId operatorId = new OperatorId("zyx12345");
        UserId userId = new UserId("abc12345");

        // 実行
        MnpOutChargeInput sut = new MnpOutChargeInput(operatorId, userId, new MnpOutItemCode(),
                new MnpOutFee(), "20140701000000");
        Property2BobioConverter actual = new Property2BobioConverter(sut);

        // 確認
        BobioOutputData expected = createBobioOutputData();
        assertThat(actual.convert().buildBobioData(), is(expected.buildBobioData()));
    }

    /**
     * このシナリオはsosaidに"system"が使えないので
     * ユーザーIDを使う。
     */

    private BobioOutputData createBobioOutputData(){

        BobioOutputData expected = new BobioOutputData();
        expected.put("invoke_scenario_name", "ASPLe_createScmCharge");
        expected.put("sosaid", "zyx12345");
        expected.put("hi_sosaid", "abc12345");
        expected.put("shohinc", "0AA0002986");
        expected.put("price", "3000");
        expected.put("charge_time", "20140701000000");

        return expected;

    }

}