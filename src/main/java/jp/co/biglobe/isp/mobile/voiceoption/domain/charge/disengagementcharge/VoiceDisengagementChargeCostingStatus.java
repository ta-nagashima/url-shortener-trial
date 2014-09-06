package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.disengagementcharge;

import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoiceDisengagementChargeCostingStatus implements ApiValue {
    COST("cost" /* 文字列リテラルの変更禁止 */),
    NOT_COST("not_cost" /* 文字列リテラルの変更禁止 */);

    @Getter
    private final String noRefactoringValue;

}
