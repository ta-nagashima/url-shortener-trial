package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class DisengagementChargeCostingEndMonthTest {

    private static final String LAST_MONTH = new DateTime().plusMonths(-1).toString("yyyyMM");

    private static final String THIS_MONTH = new DateTime().toString("yyyyMM");

    private static final String NEXT_MONTH = new DateTime().plusMonths(1).toString("yyyyMM");


    public static class _isAfterNowMonthのテスト {

        @Test
        public void _過去月のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(LAST_MONTH);

            // 実行・評価
            assertFalse(sut.isAfterNowMonth());

        }

        @Test
        public void _同月のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(THIS_MONTH);

            // 実行・評価
            assertFalse(sut.isAfterNowMonth());

        }

        @Test
        public void _未来のときTrue() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(NEXT_MONTH);

            // 実行・評価
            assertTrue(sut.isAfterNowMonth());

        }

    }

    public static class _isEqualNowMonthのテスト {

        @Test
        public void _過去月のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(LAST_MONTH);

            // 実行・評価
            assertFalse(sut.isEqualNowMonth());

        }

        @Test
        public void _同月のときTrue() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(THIS_MONTH);

            // 実行・評価
            assertTrue(sut.isEqualNowMonth());

        }

        @Test
        public void _未来のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(NEXT_MONTH);

            // 実行・評価
            assertFalse(sut.isEqualNowMonth());

        }
    }

    public static class _isBeforeNowMonthのテスト {

        @Test
        public void _過去月のときTrue() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(LAST_MONTH);

            // 実行・評価
            assertTrue(sut.isBeforeNowMonth());

        }

        @Test
        public void _同月のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(THIS_MONTH);

            // 実行・評価
            assertFalse(sut.isBeforeNowMonth());

        }

        @Test
        public void _未来のときFalse() {
            // 準備
            VoiceDisengagementChargeEndMonth sut = new VoiceDisengagementChargeEndMonth(NEXT_MONTH);

            // 実行・評価
            assertFalse(sut.isBeforeNowMonth());

        }
    }


}
