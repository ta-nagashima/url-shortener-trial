package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.exception.errorstatus.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MnpOutErrorStatus implements ErrorStatus, EnumConvertForApi {

    INVALID_STATUS(
            "ステータスが不正",
            MnpOutErrorStatusApiValue.INVALID_STATUS
            );

    @Getter
    private final String message;

    @Getter
    private final MnpOutErrorStatusApiValue mnpOutErrorStatusApiValue;

    @Override
    public String getStatus() {
        return getApiValue();
    }

    @Override
    public String getApiValue(){
        return mnpOutErrorStatusApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MnpOutErrorStatusApiValue implements ApiValue {
        INVALID_STATUS("invalid_status" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }


}
