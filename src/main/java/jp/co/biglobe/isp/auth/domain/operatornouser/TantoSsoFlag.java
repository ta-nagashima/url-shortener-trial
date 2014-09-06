package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum TantoSsoFlag implements EnumConvertForApi {

    SINGLE_SIGN_ON("シングルサインオンでのログインモード",TantoSsoFlagApiValue.SINGLE_SIGN_ON),
    OTHER("その他のログインモード",TantoSsoFlagApiValue.OTHER);

    @Getter
    private final String message;

    private final TantoSsoFlagApiValue tantoSsoFlagApiValue;

    @Override
    public String getApiValue(){
        return tantoSsoFlagApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum TantoSsoFlagApiValue implements ApiValue {
        SINGLE_SIGN_ON("1" /* 文字列リテラルの変更禁止 */),
        OTHER("0" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
