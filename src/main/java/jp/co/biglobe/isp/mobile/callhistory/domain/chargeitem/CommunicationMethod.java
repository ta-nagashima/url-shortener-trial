package jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CommunicationMethod implements EnumConvertForDb,EnumConvertForApi {
    SMS(
            CommunicationMethodApiValue.SMS,
            CommunicationMethodDbValue.SMS
    ),
    VOICE(
            CommunicationMethodApiValue.VOICE,
            CommunicationMethodDbValue.VOICE
    ),
    DENWA(
            CommunicationMethodApiValue.DENWA,
            CommunicationMethodDbValue.DENWA
    ),
    VOICE_DATA(
            CommunicationMethodApiValue.VOICE_DATA,
            CommunicationMethodDbValue.VOICE_DATA
    );

    private final ApiValue apiValue;

    private final DbValue dbValue;

    public String getApiValue(){
        return apiValue.getNoRefactoringValue();
    }

    public String getDbValue(){
        return dbValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    private enum CommunicationMethodApiValue implements ApiValue {
        SMS("ＳＭＳ" /* 文字列リテラルの変更禁止 */),
        VOICE("音声通話" /* 文字列リテラルの変更禁止 */),
        DENWA("ＢＩＧＬＯＢＥでんわ" /* 文字列リテラルの変更禁止 */),
        VOICE_DATA("音声通話（デジタル）" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum CommunicationMethodDbValue implements DbValue {
        SMS("sms" /* 文字列リテラルの変更禁止 */),
        VOICE("voice" /* 文字列リテラルの変更禁止 */),
        DENWA("denwa" /* 文字列リテラルの変更禁止 */),
        VOICE_DATA("voice_data" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }



}
