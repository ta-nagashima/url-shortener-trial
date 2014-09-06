package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.mnpoutcharge;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MnpOutFeeTest {

    @Test
    public void _商品コードの確認() throws Exception{

        Class<MnpOutFee> c = MnpOutFee.class;

        Field f1 = c.getDeclaredField( "value" );
        f1.setAccessible( true );

        assertThat(3000, is((Integer)f1.get(new MnpOutFee())));

    }

}
