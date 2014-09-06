package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.isp.mobile.voiceoption.domain.exception.VoiceEngagementInvalidStatusException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentificationBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.NotExistMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpInBuilder;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpreservationnumbervalidityterm.MnpReservationNumberValidityTerm;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.DisengagementCheckStatus;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class ValidVoiceEngagementTest {

    private static final DateTime THIS_MONTH = new DateTime();

    private static final DateTime LAST_MONTH = THIS_MONTH.minusSeconds(1);

    private static final DateTime NEXT_MONTH = THIS_MONTH.plusSeconds(1);

    public static class getDisEngagementCheckStatusのテスト {

        @Test
        public void 契約中_転出実績なし_転出処理なしのときに解約可能() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_MNP_OUT;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ENGAGED).build();

            MnpOut mnpout = new NotExistMnpOut();
            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 契約中_過去に転出キャンセルの実績あり_転出処理なしのときに解約可能() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_MNP_OUT;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ENGAGED).build();

            MnpOut mnpout = new ValidMnpOutBuilder()
                    .mnpOutStatus(MnpOutStatus.MNP_OUT_CANCEL)
                    .mnpOutCancelReason(MnpOutCancelReason.USER_REQUEST)
                    .build();
            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 契約中_転出あり_転出依頼前のとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.REQUEST_WAITING;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ENGAGED).build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.REQUEST_WAITING).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 契約中_転出あり_転出依頼済みのとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.MNP_RESERVATION_NUMBER_WAITING;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ENGAGED).build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 契約中_転出あり_転出番号発行済みのとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.MNP_OUT_WAITING;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ENGAGED).build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 解約予約中のとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_VOICE_OPTION;

            ValidVoiceEngagement sut = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(NEXT_MONTH)
                    .build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 解約中のとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_VOICE_OPTION;

            ValidVoiceEngagement sut = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(LAST_MONTH)
                    .build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_WAITING).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void 申し込み中のとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_VOICE_OPTION;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.ORDERED).build();

            MnpOut mnpout = new NotExistMnpOut();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void キャンセル済みのとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_VOICE_OPTION;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.CANCELED).build();

            MnpOut mnpout = new NotExistMnpOut();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

        @Test
        public void キャンセル済みでMNP転出ありのとき解約不可() {

            // 準備
            DisengagementCheckStatus expected = DisengagementCheckStatus.NO_VOICE_OPTION;

            ValidVoiceEngagement sut
                    = new ValidVoiceEngagementBuilder().voiceEngagementStatus(VoiceEngagementStatus.CANCELED).build();

            MnpOut mnpout = new ValidMnpOutBuilder().mnpOutStatus(MnpOutStatus.MNP_OUT_COMPLETION).build();

            // 実行
            DisengagementCheckStatus actual = sut.verifyDisengagementCheckStatus(mnpout);

            // 評価
            assertThat(actual, is(expected));
        }

    }

    public static class isDisengagedのテスト {

        private Method method;

        @Before
        public void setup() throws NoSuchMethodException {

            Method method = ValidVoiceEngagement.class.getDeclaredMethod("isDisengaged", Date.class);

            method.setAccessible(true);

            this.method = method;

        }

        @Test
        public void ステータスが解約中で契約終了日時が過去のとき解約済み() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, NEXT_MONTH.toDate());

            assertTrue(actual);
        }

        @Test
        public void ステータスが解約中で契約終了日時が現在の日時と等しいとき解約予約中() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, THIS_MONTH.toDate());

            assertFalse(actual);
        }

        @Test
        public void ステータスが解約中で契約終了日時が未来のとき解約予約中() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, LAST_MONTH.toDate());

            assertFalse(actual);
        }

    }

    public static class isDisengageReservedのテスト {

        private Method method;

        @Before
        public void setup() throws NoSuchMethodException {

            Method method = ValidVoiceEngagement.class.getDeclaredMethod("isDisengageReserved", Date.class);

            method.setAccessible(true);

            this.method = method;

        }

        @Test
        public void ステータスが解約中で契約終了日時が過去のとき解約済み() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, NEXT_MONTH.toDate());

            assertFalse(actual);
        }

        @Test
        public void ステータスが解約中で契約終了日時が現在の日時と等しいとき解約予約中() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, THIS_MONTH.toDate());

            assertTrue(actual);
        }

        @Test
        public void ステータスが解約中で契約終了日時が未来のとき解約予約中() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                    .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                    .voiceEngagementEndDateTime(THIS_MONTH)
                    .build();

            boolean actual = (boolean) method.invoke(validVoiceEngagement, LAST_MONTH.toDate());

            assertTrue(actual);
        }

    }

    @RunWith(Enclosed.class)
    public static class isNecessaryToMnpOutIntentionCheckのテスト {

        public static class _ORDER {

            ValidVoiceEngagement validVoiceEngagement;
            MnpIn mnpIn;
            MnpOut mnpOut;

            @Before
            public void setUp() {

                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .build();

                mnpIn = mock(MnpIn.class);
                mnpOut = mock(MnpOut.class);
            }


            @Test
            public void MnpInがありMnpOutが無効ときにtrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertTrue(actual);
            }

            @Test
            public void MnpInがありMnpOutが有効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが無効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが有効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

        }

        public static class _ENGAGED {

            ValidVoiceEngagement validVoiceEngagement;
            MnpIn mnpIn;
            MnpOut mnpOut;

            @Before
            public void setUp() {

                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .voiceEngagementStatus(VoiceEngagementStatus.ENGAGED)
                        .build();

                mnpIn = mock(MnpIn.class);
                mnpOut = mock(MnpOut.class);
            }


            @Test
            public void MnpInがありMnpOutが無効ときにtrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertTrue(actual);
            }

            @Test
            public void MnpInがありMnpOutが有効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが無効のときtrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertTrue(actual);
            }

            @Test
            public void MnpInがなくMnpOutが有効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

        }

        public static class _CANCEL {

            ValidVoiceEngagement validVoiceEngagement;
            MnpIn mnpIn;
            MnpOut mnpOut;

            @Before
            public void setUp() {

                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .voiceEngagementStatus(VoiceEngagementStatus.CANCELED)
                        .build();

                mnpIn = mock(MnpIn.class);
                mnpOut = mock(MnpOut.class);
            }


            @Test
            public void MnpInがありMnpOutが無効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがありMnpOutが有効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが無効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが有効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

        }

        public static class _DISENGAGE_RESERVED {

            ValidVoiceEngagement validVoiceEngagement;
            MnpIn mnpIn;
            MnpOut mnpOut;

            @Before
            public void setUp() {

                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                        .voiceEngagementEndDateTime(new DateTime().plusDays(1))
                        .build();

                mnpIn = mock(MnpIn.class);
                mnpOut = mock(MnpOut.class);
            }

            @Test
            public void MnpInがありMnpOutが無効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがありMnpOutが有効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが無効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが有効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

        }

        public static class _DISENGAGED {

            ValidVoiceEngagement validVoiceEngagement;
            MnpIn mnpIn;
            MnpOut mnpOut;

            @Before
            public void setUp() {

                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                        .voiceEngagementEndDateTime(new DateTime().minusDays(1))
                        .build();

                mnpIn = mock(MnpIn.class);
                mnpOut = mock(MnpOut.class);
            }


            @Test
            public void MnpInがありMnpOutが無効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがありMnpOutが有効ときにfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(true);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが無効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(false);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

            @Test
            public void MnpInがなくMnpOutが有効のときfalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

                // 準備
                when(mnpIn.isExist()).thenReturn(false);
                when(mnpOut.isValidMnpOut()).thenReturn(true);

                boolean actual = validVoiceEngagement.isNecessaryToMnpOutIntentionCheck(mnpIn, mnpOut);

                assertFalse(actual);
            }

        }

    }

    @RunWith(Enclosed.class)
    public static class outputCancelListのテスト {

        public static class 音声契約4パターン {

            ValidIdentification validIdentification;

            MnpIn mnpIn;

            @Before
            public void setUp() {
                validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.DOCUMENT_WAITING)
                        .build();
                mnpIn = new NotExistMnpIn();
            }

            @Test
            public void ORDERED() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .build();

                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification);
                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void ENGAGED() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ENGAGED)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void DISENGAGED() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.DISENGAGED)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void CANCELED() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.CANCELED)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }
        }

        public static class 本人確認ステータス4パターン {

            ValidVoiceEngagement validVoiceEngagement;

            MnpIn mnpIn;

            @Before
            public void setup() {
                validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .build();

                mnpIn = new NotExistMnpIn();
            }

            @Test
            public void DOCUMENT_WAITING() {

                ValidIdentification validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.DOCUMENT_WAITING)
                        .build();

                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification);

                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
            }

            @Test
            public void OUTBOUND_NOW() {

                ValidIdentification validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .build();

                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification);

                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void IDENTIFICATION_WAITING() {

                ValidIdentification validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.IDENTIFICATION_WAITING)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void OK() {

                ValidIdentification validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }
        }

        public static class キャンセルリスト出力済みフラグ {

            ValidIdentification validIdentification;

            MnpIn mnpIn;

            @Before
            public void setUp() {
                validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.DOCUMENT_WAITING)
                        .build();

                mnpIn = new NotExistMnpIn();
            }

            @Test
            public void NOT_OUTPUT() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .build();

                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification);
                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void OUTPUT() {

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }
        }

        public static class 期間 {

            ValidIdentification validIdentification;
            private DateTime now;

            @Before
            public void setUp() {
                validIdentification = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.DOCUMENT_WAITING)
                        .build();
                now = new DateTime();
            }

            @Test(expected = VoiceEngagementInvalidStatusException.class)
            public void mnpなし29日間は出力しない() {

                DateTime limitDate = now.minusDays(VoiceEngagementOrderValidityTerm.VALIDITY_TERM - 2);
                MnpIn mnpIn = new NotExistMnpIn();

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .newOrderInfo(new NewOrderInfo(
                                new VoiceSystemReceiptDateTime(limitDate.toDate()),
                                new VoiceUserOrderDate(limitDate.toString("yyyyMMdd")),
                                new VoiceJoinCode("webyn002")
                        ))
                        .build();

                validVoiceEngagement.outputCancelList(validIdentification);
            }

            @Test
            public void mnpなし30日なら出力する() {

                DateTime limitDate = now.minusDays(VoiceEngagementOrderValidityTerm.VALIDITY_TERM - 1);
                MnpIn mnpIn = new NotExistMnpIn();

                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
                        .newOrderInfo(new NewOrderInfo(
                                new VoiceSystemReceiptDateTime(limitDate.toDate()),
                                new VoiceUserOrderDate(limitDate.toString("yyyyMMdd")),
                                new VoiceJoinCode("webyn002")
                        ))
                        .build();

                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification);
                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
            }

//            @Test //TODO mnpありケースはコード修正後テスト修正
//            public void mnpあり14日は出力しない() {
//
//                DateTime limitDate = now.minusDays(MnpReservationNumberValidityTerm.VALIDITY_TERM - 2);
//                MnpIn mnpIn = new ValidMnpInBuilder().buildKoike();
//
//                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
//                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
//                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
//                        .newOrderInfo(new NewOrderInfo(
//                                new VoiceSystemReceiptDateTime(limitDate.toDate()),
//                                new VoiceUserOrderDate(limitDate.toString("yyyyMMdd")),
//                                new VoiceJoinCode("webyn002")
//                        ))
//                        .build();
//
//                validVoiceEngagement.outputCancelList(validIdentification, mnpIn);
//            }
//
//            @Test
//            public void mnpあり15日は出力する() {
//
//                DateTime limitDate = now.minusDays(MnpReservationNumberValidityTerm.VALIDITY_TERM - 1);
//                MnpIn mnpIn = new ValidMnpInBuilder().buildKoike();
//
//                ValidVoiceEngagement validVoiceEngagement = new ValidVoiceEngagementBuilder()
//                        .cancelOperationListOutPutStatus(CancelListOutPutStatus.NOT_OUTPUT)
//                        .voiceEngagementStatus(VoiceEngagementStatus.ORDERED)
//                        .newOrderInfo(new NewOrderInfo(
//                                new VoiceSystemReceiptDateTime(limitDate.toDate()),
//                                new VoiceUserOrderDate(limitDate.toString("yyyyMMdd")),
//                                new VoiceJoinCode("webyn002")
//                        ))
//                        .build();
//
//                ValidVoiceEngagement actual = validVoiceEngagement.outputCancelList(validIdentification, mnpIn);
//                assertThat(actual.getCancelListOutPutStatus(), is(CancelListOutPutStatus.OUTPUT));
//            }
        }
    }
}
