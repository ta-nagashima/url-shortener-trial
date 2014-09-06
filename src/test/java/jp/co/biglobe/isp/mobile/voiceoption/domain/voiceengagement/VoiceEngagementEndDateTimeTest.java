package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class VoiceEngagementEndDateTimeTest {

    private static final DateTime FIXATION_TIME = new DateTime(2014, 1, 1, 0, 0, 0);

    private static final Date LAST_TIME = FIXATION_TIME.minusSeconds(1).toDate();

    private static final Date THIS_TIME = FIXATION_TIME.toDate();

    private static final Date NEXT_TIME = FIXATION_TIME.plusSeconds(1).toDate();

    public static class isBeforeのテスト{


        @Test
        public void 契約終了日時が過去のときTrue(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(LAST_TIME);

            // 実行・評価
            assertTrue(sut.isBefore(THIS_TIME));

        }

        @Test
        public void 契約終了日時が現在のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(THIS_TIME);

            // 実行・評価
            assertFalse(sut.isBefore(THIS_TIME));

        }

        @Test
        public void 契約終了日時が未来のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(NEXT_TIME);

            // 実行・評価
            assertFalse(sut.isBefore(THIS_TIME));

        }

    }


    public static class isEqualのテスト{


        @Test
        public void 契約終了日時が過去のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(LAST_TIME);

            // 実行・評価
            assertFalse(sut.isEqual(THIS_TIME));

        }

        @Test
        public void 契約終了日時が現在のときTrue(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(THIS_TIME);

            // 実行・評価
            assertTrue(sut.isEqual(THIS_TIME));

        }

        @Test
        public void 契約終了日時が未来のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(NEXT_TIME);

            // 実行・評価
            assertFalse(sut.isEqual(THIS_TIME));

        }

    }


    public static class isAfterのテスト{

        @Test
        public void 契約終了日時が過去のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(LAST_TIME);

            // 実行・評価
            assertFalse(sut.isAfter(THIS_TIME));

        }

        @Test
        public void 契約終了日時が現在のときFalse(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(THIS_TIME);

            // 実行・評価
            assertFalse(sut.isAfter(THIS_TIME));

        }

        @Test
        public void 契約終了日時が未来のときTrue(){
            // 準備
            VoiceEngagementEndDateTime sut = new VoiceEngagementEndDateTime(NEXT_TIME);

            // 実行・評価
            assertTrue(sut.isAfter(THIS_TIME));

        }

    }
}
