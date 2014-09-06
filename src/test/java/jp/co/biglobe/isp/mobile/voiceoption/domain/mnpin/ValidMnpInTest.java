package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidMnpInTest {

    @Test
    public void equalsMnpInParentInfoのテスト_personalinfoが違うときtrue(){

        // 準備
        ValidMnpIn sut = new ValidMnpInBuilder().build();

        ValidMnpIn validMnpIn = new ValidMnpInBuilder().buildKoike();

        assertTrue(sut.equalsMnpInParentInfo(validMnpIn));
    }

    @Test
    public void equalsMnpInParentInfoのテスト_activationDateが違うときtrue(){

        // 準備
        ValidMnpIn sut = new ValidMnpInBuilder().build();

        ValidMnpIn validMnpIn = new ValidMnpInBuilder().activationDueDate("20140601").build();

        assertTrue(sut.equalsMnpInParentInfo(validMnpIn));

    }

    @Test
    public void equalsMnpInParentInfoのテスト_契約番号が違うときfalse(){

        // 準備
        ValidMnpIn sut = new ValidMnpInBuilder().build();

        ValidMnpIn validMnpIn = new ValidMnpInBuilder().voiceEngagementNumber(2).build();

        assertFalse(sut.equalsMnpInParentInfo(validMnpIn));

    }

    @Test
    public void equalsMnpInParentInfoのテスト_MSISDNが違うときfalse(){

        // 準備
        ValidMnpIn sut = new ValidMnpInBuilder().build();

        ValidMnpIn validMnpIn = new ValidMnpInBuilder().voiceMsisdn("999-9999-9999").build();

        assertFalse(sut.equalsMnpInParentInfo(validMnpIn));

    }

    @Test
    public void equalsMnpInParentInfoのテスト_予約番号が違うときfalse(){

        // 準備
        ValidMnpIn sut = new ValidMnpInBuilder().build();

        ValidMnpIn validMnpIn = new ValidMnpInBuilder().mnpReservationNumber("00-111-11111").build();

        assertFalse(sut.equalsMnpInParentInfo(validMnpIn));

    }

}