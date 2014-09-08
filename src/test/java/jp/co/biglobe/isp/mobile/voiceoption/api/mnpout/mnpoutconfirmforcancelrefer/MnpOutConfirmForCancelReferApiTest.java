package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutconfirmforcancelrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.container.VoiceEngagementDetailReferContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutConfirmReferService;
import jp.co.biglobe.test.util.response.ReferApiResponseAssert;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Mockitoを作ったコントローラーのテストのサンプル
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

/**
 * @ContextConfigurationではコントローラーテスト用に作ったbean定義ファイルを指定する
 */
@ContextConfiguration(locations = {"classpath:context-for-api-test.xml"})
public class MnpOutConfirmForCancelReferApiTest {


    private static final String URI = "/voiceoption/outside/skip/mnpoutconfirmforcancel/refer";

    /**
     * モック化するサービス層をAutowiredする。
     * この時点でMockオブジェクトがインジェクトされる。
     */
    @Autowired
    MnpOutConfirmReferService mnpOutConfirmReferServiceMock;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private VoiceEngagementDetailReferContainer containerMock;

    @Before
    public void setup() throws DatabaseUnitException, SQLException, IOException {

        /**
         * サービスクラスが返却するオブジェクトをモック化
         */
        containerMock = Mockito.mock(VoiceEngagementDetailReferContainer.class);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void _NECESSARY_MNPIN() throws Exception {

        /**
         * ここから、containerMockオブジェクトの動作の定義
         * （コードを読んだままです）
         */
        when(containerMock.isNecessaryToMnpOutIntentionCheck()).thenReturn(true);
        when(containerMock.getVoiceEngagementNumberApiValue()).thenReturn("1");
        when(containerMock.isMnpInExist()).thenReturn(true);
        when(containerMock.getMnpInMsisdnApiValue()).thenReturn("111-2222-3333");

        /**
         * mnpOutConfirmReferServiceMock#referを実行したときに、上記（７７〜８０行目）の
         * 動作をするcontainerMockオブジェクトを返却する。
         * ※引数のanyキーワードは、「どの値が来ても」の意味
         */
        when(mnpOutConfirmReferServiceMock.refer(any(LteThreeGEngagementNumber.class))).thenReturn(containerMock);

        /**
         * ここから下は今まで通り
         */
        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI)
                .param("lteThreeGEngagementNumberForm", "00000001"));

        // 確認・JASON
        ReferApiResponseAssert.assertJsonPath(resultActions);
        resultActions.andExpect(jsonPath("$.necessaryToMnpOutIntentionCheck").value("necessary"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.voiceEngagementNumber").value("1"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.mnpInMsisdn").value("111-2222-3333"));

    }

    @Test
    public void _NECESSARY_NOMNPIN() throws Exception {

        when(containerMock.isNecessaryToMnpOutIntentionCheck()).thenReturn(true);
        when(containerMock.getVoiceEngagementNumberApiValue()).thenReturn("1");
        when(containerMock.isMnpInExist()).thenReturn(false);

        when(mnpOutConfirmReferServiceMock.refer(any(LteThreeGEngagementNumber.class))).thenReturn(containerMock);

        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI)
                .param("lteThreeGEngagementNumberForm", "00000001"));

        // 確認・JASON
        ReferApiResponseAssert.assertJsonPath(resultActions);
        resultActions.andExpect(jsonPath("$.necessaryToMnpOutIntentionCheck").value("necessary"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.voiceEngagementNumber").value("1"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.mnpInMsisdn").doesNotExist());
    }

    @Test
    public void _UNNECESSARY_NOMNPIN() throws Exception {

        when(containerMock.isNecessaryToMnpOutIntentionCheck()).thenReturn(false);
        when(containerMock.getVoiceEngagementNumberApiValue()).thenReturn("1");

        when(mnpOutConfirmReferServiceMock.refer(any(LteThreeGEngagementNumber.class))).thenReturn(containerMock);

        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI)
                .param("lteThreeGEngagementNumberForm", "00000001"));

        // 確認・JASON
        ReferApiResponseAssert.assertJsonPath(resultActions);
        resultActions.andExpect(jsonPath("$.necessaryToMnpOutIntentionCheck").value("unnecessary"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.voiceEngagementNumber").value("1"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.mnpInMsisdn").doesNotExist());

    }

    @Test
    public void _UNNECESSARY_MNPIN() throws Exception {

        when(containerMock.isNecessaryToMnpOutIntentionCheck()).thenReturn(false);
        when(containerMock.getVoiceEngagementNumberApiValue()).thenReturn("1");
        when(containerMock.isMnpInExist()).thenReturn(true);
        when(containerMock.getMnpInMsisdnApiValue()).thenReturn("111-2222-3333");

        when(mnpOutConfirmReferServiceMock.refer(any(LteThreeGEngagementNumber.class))).thenReturn(containerMock);

        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI)
                .param("lteThreeGEngagementNumberForm", "00000001"));

        // 確認・JASON
        ReferApiResponseAssert.assertJsonPath(resultActions);
        resultActions.andExpect(jsonPath("$.necessaryToMnpOutIntentionCheck").value("unnecessary"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.voiceEngagementNumber").value("1"));
        resultActions.andExpect(jsonPath("$.intentionCheckInfo.mnpInMsisdn").value("111-2222-3333"));

    }


}
