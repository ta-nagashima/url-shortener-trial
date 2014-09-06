package jp.co.biglobe.isp.mobile.voiceoption.domain.policy;

import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * APIとして公開される値（文字列リテラル）の定義
 */
@AllArgsConstructor
enum MnpOutDetailType implements ApiValue {
    NO_MNP_IN("no_mnp_in" /* 文字列リテラルの変更禁止 */),
    NO_MNP_OUT("no_mnp_out" /* 文字列リテラルの変更禁止 */),
    REQUEST_WAITING("request_waiting" /* 文字列リテラルの変更禁止 */),
    MNP_RESERVATION_NUMBER_WAITING("mnp_reservation_number_waiting" /* 文字列リテラルの変更禁止 */),
    MNP_OUT_WAITING("mnp_out_waiting" /* 文字列リテラルの変更禁止 */),
    MNP_OUT_DONE("mnp_out_done" /* 文字列リテラルの変更禁止 */),
    NO_IDENTIFICATION("no_identification" /* 文字列リテラルの変更禁止 */),
    NO_VOICE_OPTION("no_voice_option" /* 文字列リテラルの変更禁止 */);

    @Getter
    private final String noRefactoringValue;
}

