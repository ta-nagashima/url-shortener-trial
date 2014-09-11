package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ValidMnpOutTest {

    public static class _isExistMnpOutIntentionのテスト {

        @Test
        public void _キャンセル済みの時false(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_CANCEL).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertFalse(actual);
        }

        @Test
        public void _リクエストなしの時false(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.NO_REQUEST).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertFalse(actual);
        }

        @Test
        public void _事務局への転出依頼待ちのときtrue(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.REQUEST_WAITING).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertTrue(actual);
        }

        @Test
        public void _予約番号発行待ちのときtrue(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertTrue(actual);
        }

        @Test
        public void _MNP転出完了のときtrue(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_COMPLETION).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertTrue(actual);
        }


        @Test
        public void _転出待ちで予約番号が有効のときtrue(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING).mnpOutReservationNumber(new DateTime()).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertTrue(actual);
        }

        /**
         * v1.0.0では予約番号の有効期限が切れたときは手運用でキャンセルされるため、trueにしている。
         */
        @Test
        public void _転出待ちで予約番号が無効のときtrue(){

            // 準備
            ValidMnpOut sut = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING).mnpOutReservationNumber(new DateTime().minusDays(1)).build();

            // 実行
            boolean actual = sut.isValidMnpOut();

            // 評価
            assertTrue(actual);
        }





    }






}