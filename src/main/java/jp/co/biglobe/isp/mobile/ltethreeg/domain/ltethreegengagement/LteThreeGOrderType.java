package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * LTE契約が入会同時か単独申し込みかを表すステータス
 */

@AllArgsConstructor
public enum LteThreeGOrderType implements EnumConvertForDb, EnumConvertForApi {

    ORDER_WITH_JOIN("入会同時", LteThreeGOrderTypeDbValue.ORDER_WITH_JOIN, LteThreeGOrderTypeApiValue.ORDER_WITH_JOIN),
    SINGLE_ORDER("単独申し込み", LteThreeGOrderTypeDbValue.SINGLE_ORDER, LteThreeGOrderTypeApiValue.SINGLE_ORDER);

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

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum LteThreeGOrderTypeDbValue implements DbValue {
        ORDER_WITH_JOIN("0" /* 文字列リテラルの変更禁止 */),
        SINGLE_ORDER("1" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum LteThreeGOrderTypeApiValue implements ApiValue {
        ORDER_WITH_JOIN("order_with_join" /* 文字列リテラルの変更禁止 */),
        SINGLE_ORDER("single_order" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}

