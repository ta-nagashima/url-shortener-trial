package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.identificationresultid;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResultId;
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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:context.xml"})
public class IdentificationResultIdQueryMapperTest {

    @Autowired
    SqlSessionTemplate session;

    private IdentificationResultIdQueryMapper sut;

    @Autowired
    DbUnitTester tester;

    @Before
    public void setUp() throws DatabaseUnitException, SQLException, IOException {
        tester.executeAllClearTableAndSeq();
        sut = session.getMapper(IdentificationResultIdQueryMapper.class);
    }

    @Test
    public void first() {

        // 準備
        IdentificationResultId expected = new IdentificationResultId(1);

        // 実行
        IdentificationResultId actual = sut.create();

        // 評価
        assertThat(actual, is(expected));

    }


}