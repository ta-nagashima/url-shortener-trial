package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 音声事務局への発送要求状態
 */
@AllArgsConstructor
enum VoiceClerkRequestStatus implements EnumConvertForDb, EnumConvertForApi{
    UNREQUESTED(
            "発送管理との連携を保留（仮受付の場合のみ）",
            VoiceClerkRequestStatusDbValue.UNREQUESTED,
            VoiceClerkRequestStatusApiValue.UNREQUESTED,
            true),
    REQUESTED(
            "本人確認を依頼",
            VoiceClerkRequestStatusDbValue.REQUESTED,
            VoiceClerkRequestStatusApiValue.REQUESTED,
            true),
    UNNECESSARY(
            "あとからスキームSIMあり、イオン店頭SIMありで本人確認済みのため要求が不要",
            VoiceClerkRequestStatusDbValue.UNNECESSARY,
            VoiceClerkRequestStatusApiValue.UNNECESSARY,
            true);

    private final String messsage;

    private final DbValue dbValue;

    private final ApiValue apiValue;

    /**
     * 本人確認書類をアップロード
     * （いまのところ、すべての状態において、アップロード可になっている。）
     */
    @Getter
    private boolean canUpload;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }


    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceClerkRequestStatusDbValue implements DbValue {
        UNREQUESTED("unrequested" /* 文字列リテラルの変更禁止 */),
        REQUESTED("requested" /* 文字列リテラルの変更禁止 */),
        UNNECESSARY("unnecessary" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum VoiceClerkRequestStatusApiValue implements ApiValue {
        UNREQUESTED("unrequested" /* 文字列リテラルの変更禁止 */),
        REQUESTED("requested" /* 文字列リテラルの変更禁止 */),
        UNNECESSARY("unnecessary" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
