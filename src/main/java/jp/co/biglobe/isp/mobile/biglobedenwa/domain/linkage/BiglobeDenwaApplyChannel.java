package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BiglobeDenwaApplyChannel implements EnumConvertForApi, EnumConvertForDb {

    APPLY_FROM_APP(
            "アプリからの登録",
            BiglobeDenwaApplyChannelDbValue.APPLY_FROM_APP,
            BiglobeDenwaApplyChannelApiValue.APPLY_FROM_APP
    ),
    SHIPPING_FOR_USER(
            "ユーザーへの発送",
            BiglobeDenwaApplyChannelDbValue.SHIPPING_FOR_USER,
            BiglobeDenwaApplyChannelApiValue.SHIPPING_FOR_USER
    ),
    SHIPPING_FOR_STORE(
            "店舗への発送",
            BiglobeDenwaApplyChannelDbValue.SHIPPING_FOR_STORE,
            BiglobeDenwaApplyChannelApiValue.SHIPPING_FOR_STORE
    );

    private final String message;
    private final DbValue dbValue;
    private final ApiValue apiValue;

    @Override
    public String getApiValue(){return apiValue.getNoRefactoringValue();}

    @Override
    public String getDbValue(){return dbValue.getNoRefactoringValue();}

    @AllArgsConstructor
    public enum BiglobeDenwaApplyChannelDbValue implements DbValue{
        APPLY_FROM_APP("apply_from_app" /* 文字列リテラルの変更禁止 */),
        SHIPPING_FOR_USER("shipping_for_user" /* 文字列リテラルの変更禁止 */),
        SHIPPING_FOR_STORE("shipping_for_store" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    public enum BiglobeDenwaApplyChannelApiValue implements ApiValue{
        APPLY_FROM_APP("apply_from_app" /* 文字列リテラルの変更禁止 */),
        SHIPPING_FOR_USER("shipping_for_user" /* 文字列リテラルの変更禁止 */),
        SHIPPING_FOR_STORE("shipping_for_store" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}

