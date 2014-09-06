package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.VoiceMnpOutId;
import jp.co.biglobe.test.util.dbunit.DbUnitTester;
import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class MnpOutIdQueryMapperTest {

    @Autowired
    SqlSessionTemplate session;

    private MnpOutIdQueryMapper sut;

    @Autowired
    DbUnitTester tester;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
        sut = session.getMapper(MnpOutIdQueryMapper.class);
    }

    @Test
    public void createのテスト() throws Exception {
        // 準備
        VoiceMnpOutId expected = new VoiceMnpOutId(new Integer(1));

        // 実行
        VoiceMnpOutId actual = sut.create();

        // 評価
        assertThat(actual, is(expected));

    }


}
