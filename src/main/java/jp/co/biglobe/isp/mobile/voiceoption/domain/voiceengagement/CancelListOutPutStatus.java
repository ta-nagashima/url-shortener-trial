package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CancelListOutPutStatus implements EnumConvertForDb, EnumConvertForApi {

    OUTPUT(CancelOperationListOutPutStatusDbValue.OUTPUT, CancelOperationListOutPutStatusApiValue.OUTPUT, CanBeOutputCancelList.CAN_NOT_OUTPUT),
    NOT_OUTPUT(CancelOperationListOutPutStatusDbValue.NOT_OUTPUT, CancelOperationListOutPutStatusApiValue.NOT_OUTPUT, CanBeOutputCancelList.CAN_OUTPUT);

    private final DbValue dbValue;

    private final ApiValue apiValue;

    public final CanBeOutputCancelList canBeOutputCancelList;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    public boolean isCanOutputCancelList(){
        return canBeOutputCancelList.value;
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum CancelOperationListOutPutStatusDbValue implements DbValue {
        OUTPUT("output" /* 文字列リテラルの変更禁止 */),
        NOT_OUTPUT("not_output" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum CancelOperationListOutPutStatusApiValue implements ApiValue {
        OUTPUT("output" /* 文字列リテラルの変更禁止 */),
        NOT_OUTPUT("not_output" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }


    @AllArgsConstructor
    private enum CanBeOutputCancelList {
        CAN_OUTPUT(true),
        CAN_NOT_OUTPUT(false);

        private final boolean value;
    }
}
