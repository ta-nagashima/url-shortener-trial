package jp.co.biglobe.isp.sample.domain.samplecheckstatus;

import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi;
import jp.co.biglobe.lib.publication.enumeration.checkable.CheckResult;
import jp.co.biglobe.lib.publication.enumeration.checkable.Checkable;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString(includeFieldNames = false)
@AllArgsConstructor
public enum SampleCheckStatus implements BusinessStatusForApi {
    REGISTERED(CheckResult.OK, "登録済", SampleCheckStatusApiValue.REGISTERED),
    UNREGISTERED(CheckResult.NG, "未登録", SampleCheckStatusApiValue.UNREGISTERED),
    INVALID(CheckResult.NG, "不整合", SampleCheckStatusApiValue.INVALID);


    @Getter
    private final Checkable checkable;

    @Getter
    private final String message;

    @Getter
    private final SampleCheckStatusApiValue sampleCheckStatusApiValue;

    @Override
    public String getApiValue() {
        return sampleCheckStatusApiValue.getNoRefactoringValue();
    }

    /**
     * APIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum SampleCheckStatusApiValue implements ApiValue {
        REGISTERED("registered" /* 文字列リテラルの変更禁止 */),
        UNREGISTERED("un_registered" /* 文字列リテラルの変更禁止 */),
        INVALID("invalid" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
