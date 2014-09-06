package jp.co.biglobe.isp.mobile.biglobemember.domain.course;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 法人会員か個人会員かを表すクラス。
 * 名前どうしよう・・・
 */
@ToString(includeFieldNames=false)
@AllArgsConstructor
public enum ContractType implements EnumConvertForApi {
    INDIVIDUAL("個人会員",ContractTypeApiValue.INDIVIDUAL),
    CORPORATION("法人会員", ContractTypeApiValue.CORPORATION);

    /**
     * 表示名
     */
    @Getter
    private final String label;

    private final ContractTypeApiValue contractTypeApiValue;


    @Override
    public String getApiValue(){
        return contractTypeApiValue.getNoRefactoringValue();
    }

    public boolean isCorporation(){
        return this.equals(ContractType.CORPORATION);
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum ContractTypeApiValue implements ApiValue {
        INDIVIDUAL("individual" /* 文字列リテラルの変更禁止 */),
        CORPORATION("corporation" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
