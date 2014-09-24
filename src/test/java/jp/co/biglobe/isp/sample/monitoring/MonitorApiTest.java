package jp.co.biglobe.isp.sample.monitoring;

import jp.co.biglobe.lib.plugin.event.RequestEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class MonitorApiTest {

    private static final String URI = "/monitoring/check";
    private static final String URI_REQUEST_ID = "/monitoring/requestId";

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private RequestEvent requestEvent;


    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @After
    public void tearDown() throws IOException {
    }

    @Test
    public void check_正常() throws Exception {

        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI));

        // 確認：結果（API固有部分）
        resultActions.andExpect(content().string("ok"));
    }

    @Test
    public void check_requestIdの初期化() throws Exception {

        // 実行
        ResultActions resultActions = mockMvc.perform(post(URI_REQUEST_ID));

        // 確認：結果（API固有部分）
        resultActions.andExpect(content().string(not("un_initialized")));
    }

}
