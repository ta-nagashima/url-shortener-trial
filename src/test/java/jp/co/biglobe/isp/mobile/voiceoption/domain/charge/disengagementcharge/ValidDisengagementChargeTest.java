package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class ValidDisengagementChargeTest {

    private static final DateTime FIXED_DATETIME = new DateTime();

    private static final String THIS_MONTH = FIXED_DATETIME.toString("yyyyMM");

    private static final String LAST_MONTH = FIXED_DATETIME.plusMonths(-1).toString("yyyyMM");

    private static final String NEXT_MONTH = FIXED_DATETIME.plusMonths(1).toString("yyyyMM");


    public static class isChargeのテスト {

        @Test
        public void _解約手数料終了月が過去月ならFalse() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(LAST_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );


            // 実行・評価
            assertFalse(sut.isCharge());

        }

        @Test
        public void _解約手数料終了月が当月ならTrue() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(THIS_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );

            // 実行・評価
            assertTrue(sut.isCharge());

        }

        @Test
        public void _解約手数料終了月が翌月ならTrue() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(NEXT_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );

            // 実行・評価
            assertTrue(sut.isCharge());

        }
    }

    public static class getApiValueForCostingStatusのテスト {

        @Test
        public void _解約手数料終了月が過去月ならnot_cost() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(LAST_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );

            // 実行
            String actual = sut.getApiValueForCostingStatus();


            // 実行・評価
            assertThat(actual, is("not_cost"));

        }


        @Test
        public void _解約手数料終了月が当月ならcost() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(THIS_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );

            // 実行
            String actual = sut.getApiValueForCostingStatus();


            // 実行・評価
            assertThat(actual, is("cost"));

        }

        @Test
        public void _解約手数料終了月が翌月ならcost() {

            // 準備
            ValidDisengagementCharge sut = new ValidDisengagementCharge(
                    new VoiceEngagementNumber(1),
                    new VoiceDisengagementChargeEndMonth(NEXT_MONTH),
                    new VoiceDisengagementChargeAmount(6000)
            );

            // 実行
            String actual = sut.getApiValueForCostingStatus();


            // 実行・評価
            assertThat(actual, is("cost"));

        }
    }
}
