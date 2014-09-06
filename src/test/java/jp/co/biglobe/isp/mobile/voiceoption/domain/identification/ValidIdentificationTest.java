package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResultBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Enclosed;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class ValidIdentificationTest {

    @RunWith(Enclosed.class)
    public static class reflectIdentificationResult{

        private static ValidIdentificationResult validIdentificationResult = new ValidIdentificationResultBuilder().build();

        public static class OK{

            @Test
            public void _資料待ちからOK(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder().build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationOkResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }

            @Test
            public void _本人確認待ちからOK(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .validUpload()
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.IDENTIFICATION_WAITING)
                        .validUpload()
                        .build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationOkResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }

            @Test
            public void _OB待ちからOK(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationOkResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }
        }

        public static class NG{

            @Test
            public void _資料待ちからOB待ち(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder().build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationNgResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }

            @Test
            public void _本人確認待ちからOB待ち(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.IDENTIFICATION_WAITING)
                        .validUpload()
                        .build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationNgResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }

            @Test
            public void _OB待ちからOB待ち(){

                // 準備
                ValidIdentification expected = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .validIdentificationResult()
                        .build();

                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OUTBOUND_NOW)
                        .build();

                // 実行
                ValidIdentification actual = sut.reflectIdentificationNgResult(validIdentificationResult);

                // 評価
                assertThat(actual, is(expected));
            }
        }

        public static class ERROR{

            @Test(expected = SystemCheckException.class)
            public void _OKからOK(){

                // 準備
                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .validIdentificationResult()
                        .validUpload()
                        .build();

                // 実行
                sut.reflectIdentificationOkResult(validIdentificationResult);

            }

            @Test(expected = SystemCheckException.class)
            public void _OKからNG(){

                // 準備
                ValidIdentification sut = new ValidIdentificationBuilder()
                        .identificationStatus(IdentificationStatus.OK)
                        .validIdentificationResult()
                        .validUpload()
                        .build();

                // 実行
                sut.reflectIdentificationOkResult(validIdentificationResult);

            }

        }

    }

}