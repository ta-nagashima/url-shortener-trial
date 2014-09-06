package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 新規かデータから音声へのSIM変更かどうかを表すステータス
 */
@AllArgsConstructor
public enum VoiceOrderType implements EnumConvertForDb, EnumConvertForApi {

    ORDER_WITH_LTE("音声オプション付きLTE契約の申し込み", VoiceOrderTypeDbValue.ORDER_WITH_LTE, VoiceOrderTypeApiValue.ORDER_WITH_LTE),
    CHANGE_DATA_TO_VOICE("データから音声へのSim変更", VoiceOrderTypeDbValue.CHANGE_DATA_TO_VOICE, VoiceOrderTypeApiValue.CHANGE_DATA_TO_VOICE);

    private final String message;

    private final DbValue dbValue;

    private final ApiValue apiValue;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceOrderTypeDbValue implements DbValue {
        ORDER_WITH_LTE("order_with_lte" /* 文字列リテラルの変更禁止 */),
        CHANGE_DATA_TO_VOICE("change_data_to_voice" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceOrderTypeApiValue implements ApiValue {
        ORDER_WITH_LTE("order_with_lte" /* 文字列リテラルの変更禁止 */),
        CHANGE_DATA_TO_VOICE("change_data_to_voice" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
