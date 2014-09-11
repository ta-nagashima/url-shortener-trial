package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ExpireDateTest {

    public static class _isValidのテスト {

        @Test
        public void _valueが現在より後のときtrue(){

            // 準備
            ExpireDate sut = new ExpireDate(new DateTime().plusDays(1).toString("yyyyMMdd"));

            // 実行
            boolean actual = sut.isValid();

            // 評価
            assertTrue(actual);
        }

        @Test
        public void _valueが今日のときtrue(){

            // 準備
            ExpireDate sut = new ExpireDate(new DateTime().toString("yyyyMMdd"));

            // 実行
            boolean actual = sut.isValid();

            // 評価
            assertTrue(actual);
        }


        @Test
        public void _valueが過去日のときfalse(){

            // 準備
            ExpireDate sut = new ExpireDate(new DateTime().minusDays(1).toString("yyyyMMdd"));

            // 実行
            boolean actual = sut.isValid();

            // 評価
            assertFalse(actual);
        }




    }




}