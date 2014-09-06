package jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForScenario;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MnpGender implements EnumConvertForDb, EnumConvertForApi {
    MALE(MnpGenderDbValue.MALE, MnpGenderApiValue.MALE),
    FEMALE(MnpGenderDbValue.FEMALE, MnpGenderApiValue.FEMALE),
    UNKNOWN(MnpGenderDbValue.UNKNOWN, MnpGenderApiValue.UNKNOWN);

    private final DbValue dbValue;

    private final ApiValue apiValue;

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
    private enum MnpGenderDbValue implements DbValue {
        MALE("male" /* 文字列リテラルの変更禁止 */),
        FEMALE("female" /* 文字列リテラルの変更禁止 */),
        UNKNOWN("unknown" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MnpGenderApiValue implements ApiValue {
        MALE("male" /* 文字列リテラルの変更禁止 */),
        FEMALE("female" /* 文字列リテラルの変更禁止 */),
        UNKNOWN("unknown" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
