package jp.co.biglobe.isp.sample.user.domain.sampleuser;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum SampleGender implements EnumConvertForDb {
    MALE("男性", SampleGenderDbValue.MALE),
    FEMALE("女性", SampleGenderDbValue.FEMALE);

    private final String label;

    private final DbValue dbValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum SampleGenderDbValue implements DbValue {
        MALE("male" /* 文字列リテラルの変更禁止 */),
        FEMALE("female" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
