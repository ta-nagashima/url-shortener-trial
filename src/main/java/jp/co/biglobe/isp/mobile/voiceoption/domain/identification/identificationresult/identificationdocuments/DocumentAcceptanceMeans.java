package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments;

import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DocumentAcceptanceMeans implements EnumConvertForDb, EnumConvertForApi {
    FAX(DocumentAcceptanceMeansDbValue.FAX, DocumentAcceptanceMeansApiValue.FAX),
    WEB(DocumentAcceptanceMeansDbValue.WEB, DocumentAcceptanceMeansApiValue.WEB),
    POST(DocumentAcceptanceMeansDbValue.POST, DocumentAcceptanceMeansApiValue.POST);

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
    * APIに提示される値（文字列リテラル）の定義
    */
    @AllArgsConstructor
    private enum DocumentAcceptanceMeansApiValue implements ApiValue {
        FAX("fax" /* 文字列リテラルの変更禁止 */),
        WEB("web" /* 文字列リテラルの変更禁止 */),
        POST("post" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum DocumentAcceptanceMeansDbValue implements DbValue {
        FAX("fax" /* 文字列リテラルの変更禁止 */),
        WEB("web" /* 文字列リテラルの変更禁止 */),
        POST("post" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
