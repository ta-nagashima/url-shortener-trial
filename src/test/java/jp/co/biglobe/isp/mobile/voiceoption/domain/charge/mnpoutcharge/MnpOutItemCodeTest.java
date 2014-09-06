package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MnpOutItemCodeTest {

    @Test
    public void _商品コードの確認() throws Exception{

        Class<MnpOutItemCode> c = MnpOutItemCode.class;

        Field f1 = c.getDeclaredField( "value" );
        f1.setAccessible( true );

        assertThat("0AA0002986", is((String)f1.get(new MnpOutItemCode())));

    }

}
