package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoiceEngagementStatus implements EnumConvertForDb, EnumConvertForApi {

    INIT(VoiceEngagementStatusDbValue.INIT, VoiceEngagementStatusApiValue.INIT, CanDocumentUpload.NG, CanDisEngagement.NG),
    ORDERED(VoiceEngagementStatusDbValue.ORDERED, VoiceEngagementStatusApiValue.ORDERED, CanDocumentUpload.OK, CanDisEngagement.NG),
    ENGAGED(VoiceEngagementStatusDbValue.ENGAGED, VoiceEngagementStatusApiValue.ENGAGED, CanDocumentUpload.NG, CanDisEngagement.OK),
    DISENGAGED(VoiceEngagementStatusDbValue.DISENGAGED, VoiceEngagementStatusApiValue.DISENGAGED, CanDocumentUpload.NG, CanDisEngagement.NG),
    CANCELED(VoiceEngagementStatusDbValue.CANCELED, VoiceEngagementStatusApiValue.CANCELED, CanDocumentUpload.NG, CanDisEngagement.NG);


    private final DbValue dbValue;

    private final ApiValue apiValue;

    private final CanDocumentUpload canDocumentUpload;

    private final CanDisEngagement canDisEngagement;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    boolean canDocumentUploadStatus(){
        return canDocumentUpload.value;
    }

    boolean canDisEngagementStatus(){
        return canDisEngagement.value;
    }


    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceEngagementStatusDbValue implements DbValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        ORDERED("ordered" /* 文字列リテラルの変更禁止 */),
        ENGAGED("engaged" /* 文字列リテラルの変更禁止 */),
        DISENGAGED("disengaged" /* 文字列リテラルの変更禁止 */),
        CANCELED("canceled" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum VoiceEngagementStatusApiValue implements ApiValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        ORDERED("ordered" /* 文字列リテラルの変更禁止 */),
        ENGAGED("engaged" /* 文字列リテラルの変更禁止 */),
        DISENGAGED("disengaged" /* 文字列リテラルの変更禁止 */),
        CANCELED("canceled" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum CanDocumentUpload{
        OK(true),
        NG(false);

        private boolean value;
    }

    @AllArgsConstructor
    private enum CanDisEngagement{
        OK(true),
        NG(false);

        private boolean value;
    }
}
