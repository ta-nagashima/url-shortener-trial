package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;


import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * メールでの通知ステータス
 */

@AllArgsConstructor
public enum  BiglobeDenwaNotification implements EnumConvertForApi,EnumConvertForDb{
    NECESSARY("必要", BiglobeDenwaNotificationDbValue.NECESSARY,BiglobeDenwaNotificationApiValue.NECESSARY),
    UNNECESSARY("不要", BiglobeDenwaNotificationDbValue.UNNECESSARY,BiglobeDenwaNotificationApiValue.UNNECESSARY),
    DONE("通知済み", BiglobeDenwaNotificationDbValue.DONE,BiglobeDenwaNotificationApiValue.DONE);


    private final String message;
    private final DbValue dbValue;
    private final ApiValue apiValue;

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @AllArgsConstructor
    public enum BiglobeDenwaNotificationDbValue implements DbValue{
        NECESSARY("necessary"  /* 文字列リテラルの変更禁止 */),
        UNNECESSARY("unnecessary"  /* 文字列リテラルの変更禁止 */),
        DONE("done" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    public enum BiglobeDenwaNotificationApiValue implements ApiValue{
        NECESSARY("necessary"  /* 文字列リテラルの変更禁止 */),
        UNNECESSARY("unnecessary"  /* 文字列リテラルの変更禁止 */),
        DONE("done" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}
