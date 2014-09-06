package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum IdentificationStatus implements EnumConvertForDb,EnumConvertForApi {
    INIT(
            IdentificationStatusDbValue.INIT,
            IdentificationStatusApiValue.INIT,
            IdentificationStatusRefApiValue.IDENTIFICATION_WAITING,
            CanUploadStatus.CAN_NOT_UPLOAD,
            CanOutputCancelListStatus.CAN_NOT_OUTPUT
    ),
    DOCUMENT_WAITING(
            IdentificationStatusDbValue.DOCUMENT_WAITING,
            IdentificationStatusApiValue.DOCUMENT_WAITING,
            IdentificationStatusRefApiValue.IDENTIFICATION_WAITING,
            CanUploadStatus.CAN_UPLOAD,
            CanOutputCancelListStatus.CAN_OUTPUT
    ),
    IDENTIFICATION_WAITING(
            IdentificationStatusDbValue.IDENTIFICATION_WAITING,
            IdentificationStatusApiValue.IDENTIFICATION_WAITING,
            IdentificationStatusRefApiValue.IDENTIFICATION_WAITING,
            CanUploadStatus.CAN_UPLOAD,
            CanOutputCancelListStatus.CAN_NOT_OUTPUT
    ),
    OK(
            IdentificationStatusDbValue.OK,
            IdentificationStatusApiValue.OK,
            IdentificationStatusRefApiValue.OK,
            CanUploadStatus.CAN_NOT_UPLOAD,
            CanOutputCancelListStatus.CAN_NOT_OUTPUT
    ),
    OUTBOUND_NOW(
            IdentificationStatusDbValue.OUTBOUND_NOW,
            IdentificationStatusApiValue.OUTBOUND_NOW,
            IdentificationStatusRefApiValue.NG,
            CanUploadStatus.CAN_UPLOAD,
            CanOutputCancelListStatus.CAN_OUTPUT
    );

    private final DbValue dbValue;

    private final ApiValue apiValue;

    private final IdentificationStatusRefApiValue refApiValue;

    /**
     * アップロード可否
     */
    private CanUploadStatus canUpload;

    public boolean isCanUpload(){
        return this.canUpload.value;
    }

    /**
     * 本人確認未実施キャンセルリストへの出力可否
     */
    private CanOutputCancelListStatus canOutputCancelList;

    public boolean isCanOutputCancelList(){
        return this.canOutputCancelList.value;
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    public String getRefApiValue(){
        return refApiValue.getNoRefactoringValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum IdentificationStatusDbValue implements DbValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        DOCUMENT_WAITING("document_waiting" /* 文字列リテラルの変更禁止 */),
        IDENTIFICATION_WAITING("identification_waiting" /* 文字列リテラルの変更禁止 */),
        OK("ok" /* 文字列リテラルの変更禁止 */),
        OUTBOUND_NOW("outbound_now" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum IdentificationStatusApiValue implements ApiValue {
        INIT("init" /* 文字列リテラルの変更禁止 */),
        DOCUMENT_WAITING("document_waiting" /* 文字列リテラルの変更禁止 */),
        IDENTIFICATION_WAITING("identification_waiting" /* 文字列リテラルの変更禁止 */),
        OK("ok" /* 文字列リテラルの変更禁止 */),
        OUTBOUND_NOW("outbound_now" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum IdentificationStatusRefApiValue implements ApiValue {
        IDENTIFICATION_WAITING("identification_waiting" /* 文字列リテラルの変更禁止 */),
        OK("ok" /* 文字列リテラルの変更禁止 */),
        NG("ng" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    private enum CanUploadStatus {
        CAN_UPLOAD(true),
        CAN_NOT_UPLOAD(false);

        private final boolean value;
    }

    @AllArgsConstructor
    private enum CanOutputCancelListStatus {
        CAN_OUTPUT(true),
        CAN_NOT_OUTPUT(false);

        private final boolean value;
    }



}
