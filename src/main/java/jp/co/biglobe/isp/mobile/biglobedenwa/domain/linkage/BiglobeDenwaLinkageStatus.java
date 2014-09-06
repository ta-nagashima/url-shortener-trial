package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BiglobeDenwaLinkageStatus implements EnumConvertForApi,EnumConvertForDb{
    NO_LINKAGE("未登録",BiglobeDenwaRegisterStatusDbValue.NO_LINKAGE, BiglobeDenwaRegisterStatusApiValue.NO_LINKAGE),
    WAITING_REGISTER("登録連携前",BiglobeDenwaRegisterStatusDbValue.WAITING_REGISTER, BiglobeDenwaRegisterStatusApiValue.WAITING_REGISTER),
    WAITING_REGISTER_RESULT("登録連携中",BiglobeDenwaRegisterStatusDbValue.WAITING_REGISTER_RESULT, BiglobeDenwaRegisterStatusApiValue.WAITING_REGISTER_RESULT),
    REGISTERED("登録済み",BiglobeDenwaRegisterStatusDbValue.REGISTERED, BiglobeDenwaRegisterStatusApiValue.REGISTERED),
    WAITING_REMOVE("解除連携前",BiglobeDenwaRegisterStatusDbValue.WAITING_REMOVE, BiglobeDenwaRegisterStatusApiValue.WAITING_REMOVE),
    WAITING_REMOVE_RESULT("解除連携中",BiglobeDenwaRegisterStatusDbValue.WAITING_REMOVE_RESULT, BiglobeDenwaRegisterStatusApiValue.WAITING_REMOVE_RESULT),
    WAITING_REREGISTER("再登録連携前",BiglobeDenwaRegisterStatusDbValue.WAITING_REREGISTER, BiglobeDenwaRegisterStatusApiValue.WAITING_REREGISTER),
    WAITING_REREGISTER_RESULT("再登録連携中",BiglobeDenwaRegisterStatusDbValue.WAITING_REREGISTER_RESULT, BiglobeDenwaRegisterStatusApiValue.WAITING_REREGISTER_RESULT),
    UNABLE_TO_REGISTER("登録NG",BiglobeDenwaRegisterStatusDbValue.UNABLE_TO_REGISTER, BiglobeDenwaRegisterStatusApiValue.UNABLE_TO_REGISTER),
    REGISTERED_IN_OTHER("他社登録済み", BiglobeDenwaRegisterStatusDbValue.REGISTERED_IN_OTHER, BiglobeDenwaRegisterStatusApiValue.REGISTERED_IN_OTHER);

    private final String message;
    private final DbValue dbValue;
    private final ApiValue apiValue;

    @Override
    public String getApiValue(){return apiValue.getNoRefactoringValue();}

    @Override
    public String getDbValue(){return dbValue.getNoRefactoringValue();}

    @AllArgsConstructor
    public enum BiglobeDenwaRegisterStatusDbValue implements DbValue{
        NO_LINKAGE("no_linkage" /* 文字列リテラルの変更禁止 */),
        WAITING_REGISTER("waiting_register" /* 文字列リテラルの変更禁止 */),
        WAITING_REGISTER_RESULT("waiting_register_result" /* 文字列リテラルの変更禁止 */),
        REGISTERED("registered" /* 文字列リテラルの変更禁止 */),
        WAITING_REMOVE("waiting_remove" /* 文字列リテラルの変更禁止 */),
        WAITING_REMOVE_RESULT("waiting_remove_result" /* 文字列リテラルの変更禁止 */),
        WAITING_REREGISTER("waiting_reregister" /* 文字列リテラルの変更禁止 */),
        WAITING_REREGISTER_RESULT("waiting_reregister_result" /* 文字列リテラルの変更禁止 */),
        UNABLE_TO_REGISTER("unable_to_register" /* 文字列リテラルの変更禁止 */),
        REGISTERED_IN_OTHER("registered_in_other" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    @AllArgsConstructor
    public enum BiglobeDenwaRegisterStatusApiValue implements ApiValue{
        NO_LINKAGE("no_linkage" /* 文字列リテラルの変更禁止 */),
        WAITING_REGISTER("waiting_register" /* 文字列リテラルの変更禁止 */),
        WAITING_REGISTER_RESULT("waiting_register_result" /* 文字列リテラルの変更禁止 */),
        REGISTERED("registered" /* 文字列リテラルの変更禁止 */),
        WAITING_REMOVE("waiting_remove" /* 文字列リテラルの変更禁止 */),
        WAITING_REMOVE_RESULT("waiting_remove_result" /* 文字列リテラルの変更禁止 */),
        WAITING_REREGISTER("waiting_reregister" /* 文字列リテラルの変更禁止 */),
        WAITING_REREGISTER_RESULT("waiting_reregister_result" /* 文字列リテラルの変更禁止 */),
        UNABLE_TO_REGISTER("unable_to_register" /* 文字列リテラルの変更禁止 */),
        REGISTERED_IN_OTHER("registered_in_other" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

}


