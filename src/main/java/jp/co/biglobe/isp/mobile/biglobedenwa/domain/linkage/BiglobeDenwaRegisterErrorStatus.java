package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.exception.errorstatus.ErrorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  BiglobeDenwaRegisterErrorStatus implements ErrorStatus, EnumConvertForApi{

    INVALID_STATUS(
            "既に登録済みのMSISDNです",
            BiglobeDenwaRegisterErrorStatusApiValue.INVALID_STATUS
    ),
    INVALID_LINKAGE_STATUS(
            "でんわ連携のステータスが異常",
            BiglobeDenwaRegisterErrorStatusApiValue.INVALID_LINKAGE_STATUS
    ),
    INCORRECT_BIRTHDAY(
            "生年月日が違います",
            BiglobeDenwaRegisterErrorStatusApiValue.INCORRECT_BIRTHDAY
    );

    @Getter
    private final String message;

    @Getter
    private final BiglobeDenwaRegisterErrorStatusApiValue biglobeDenwaRegisterErrorStatusApiValue;

    @Override
    public String getApiValue() {
        return biglobeDenwaRegisterErrorStatusApiValue.getNoRefactoringValue();
    }

    @Override
    public String getStatus() {
        return getApiValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum BiglobeDenwaRegisterErrorStatusApiValue implements ApiValue {
        INVALID_STATUS("invalid_status" /* 文字列リテラルの変更禁止 */),
        INVALID_LINKAGE_STATUS("invalid_linkage_status" /* 文字列リテラルの変更禁止 */),
        INCORRECT_BIRTHDAY("incorrect_birthday" /* 文字列リテラルの変更禁止 */);
        @Getter
        private final String noRefactoringValue;
    }
}
