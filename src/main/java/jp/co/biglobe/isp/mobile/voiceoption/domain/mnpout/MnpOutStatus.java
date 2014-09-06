package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.extension.view.StereotypedCharacters;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForApi;
import jp.co.biglobe.lib.publication.enumeration.convert.EnumConvertForDb;
import jp.co.biglobe.lib.publication.enumeration.valuetype.ApiValue;
import jp.co.biglobe.lib.publication.enumeration.valuetype.DbValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MnpOutStatus implements EnumConvertForDb, EnumConvertForApi {
    NO_REQUEST(
            MnpOutStatusDbValue.NO_REQUEST,
            EngagementDetailApiValue.NO_REQUEST,
            ReferApiValue.NOT_EXIST,
            SimChangeCheckStatus.MNP_NEVER_ORDERED,
            ImmediateDisengagementApiValue.MNP_OUT_NOT_ORDERED,
            DisengagementCheckStatus.NO_MNP_OUT,
            MnpOutAndDisengagementCheckStatus.NO_MNP_OUT,
            NewOrderCancelCheckStatus.NO_MNP_OUT,
            MnpOutAndNewOrderCancelCheckStatus.NO_MNP_OUT,
            MnpOutApplyCheckStatus.NO_MNP_OUT,
            MnpOutApplyCancelCheckStatus.NO_MNP_OUT),
    REQUEST_WAITING(
            MnpOutStatusDbValue.REQUEST_WAITING,
            EngagementDetailApiValue.REQUEST_WAITING,
            ReferApiValue.EXIST,
            SimChangeCheckStatus.MNP_REQUEST_WAITING,
            ImmediateDisengagementApiValue.MNP_OUT_PROCESSING,
            DisengagementCheckStatus.REQUEST_WAITING,
            MnpOutAndDisengagementCheckStatus.REQUEST_WAITING,
            NewOrderCancelCheckStatus.REQUEST_WAITING,
            MnpOutAndNewOrderCancelCheckStatus.REQUEST_WAITING,
            MnpOutApplyCheckStatus.REQUEST_WAITING,
            MnpOutApplyCancelCheckStatus.REQUEST_WAITING),
    MNP_RESERVATION_NUMBER_WAITING(
            MnpOutStatusDbValue.MNP_RESERVATION_NUMBER_WAITING,
            EngagementDetailApiValue.MNP_RESERVATION_NUMBER_WAITING,
            ReferApiValue.EXIST,
            SimChangeCheckStatus.MNP_NUMBERING_WAITING,
            ImmediateDisengagementApiValue.MNP_OUT_PROCESSING,
            DisengagementCheckStatus.MNP_RESERVATION_NUMBER_WAITING,
            MnpOutAndDisengagementCheckStatus.MNP_RESERVATION_NUMBER_WAITING,
            NewOrderCancelCheckStatus.MNP_RESERVATION_NUMBER_WAITING,
            MnpOutAndNewOrderCancelCheckStatus.MNP_RESERVATION_NUMBER_WAITING,
            MnpOutApplyCheckStatus.MNP_RESERVATION_NUMBER_WAITING,
            MnpOutApplyCancelCheckStatus.MNP_RESERVATION_NUMBER_WAITING),
    MNP_OUT_WAITING(
            MnpOutStatusDbValue.MNP_OUT_WAITING,
            EngagementDetailApiValue.MNP_OUT_WAITING,
            ReferApiValue.EXIST,
            SimChangeCheckStatus.MNP_NUMBERING,
            ImmediateDisengagementApiValue.MNP_OUT_WAITING,
            DisengagementCheckStatus.MNP_OUT_WAITING,
            MnpOutAndDisengagementCheckStatus.MNP_OUT_WAITING,
            NewOrderCancelCheckStatus.MNP_OUT_WAITING,
            MnpOutAndNewOrderCancelCheckStatus.MNP_OUT_WAITING,
            MnpOutApplyCheckStatus.MNP_OUT_WAITING,
            MnpOutApplyCancelCheckStatus.MNP_OUT_WAITING),
    MNP_OUT_COMPLETION(
            MnpOutStatusDbValue.MNP_OUT_COMPLETION,
            EngagementDetailApiValue.MNP_OUT_COMPLETION,
            ReferApiValue.NOT_EXIST,
            SimChangeCheckStatus.MNP_PORT_OUT,
            ImmediateDisengagementApiValue.MNP_OUT_COMPLETION,
            DisengagementCheckStatus.MNP_OUT_DONE,
            MnpOutAndDisengagementCheckStatus.MNP_OUT_DONE,
            NewOrderCancelCheckStatus.MNP_OUT_DONE,
            MnpOutAndNewOrderCancelCheckStatus.MNP_OUT_DONE,
            MnpOutApplyCheckStatus.MNP_OUT_DONE,
            MnpOutApplyCancelCheckStatus.MNP_OUT_DONE),
    MNP_OUT_CANCEL(
            MnpOutStatusDbValue.MNP_OUT_CANCEL,
            EngagementDetailApiValue.MNP_OUT_CANCEL,
            ReferApiValue.NOT_EXIST,
            SimChangeCheckStatus.MNP_CANCELLED,
            ImmediateDisengagementApiValue.MNP_OUT_NOT_ORDERED,
            DisengagementCheckStatus.NO_MNP_OUT,
            MnpOutAndDisengagementCheckStatus.NO_MNP_OUT,
            NewOrderCancelCheckStatus.NO_MNP_OUT,
            MnpOutAndNewOrderCancelCheckStatus.NO_MNP_OUT,
            MnpOutApplyCheckStatus.NO_MNP_OUT,
            MnpOutApplyCancelCheckStatus.NO_MNP_OUT);

    private final DbValue dbValue;

    // 契約情報一覧参照API
    private final ApiValue apiValue;

    // MNP転出参照API
    private final ApiValue referApiValue;

    // SIMサイズ変更可否
    @Getter
    private final SimChangeCheckStatus simChangeCheckStatus;

    // 即時解約確認API
    @Getter
    private final ImmediateDisengagementApiValue immediateDisengagementApiValue;

    // 解約確認
    @Getter
    private final DisengagementCheckStatus disengagementCheckStatusValue;

    // MNP転出兼解約確認
    @Getter
    private final MnpOutAndDisengagementCheckStatus mnpOutAndDisengagementCheckStatusValue;

    // 新規申込キャンセル可否確認
    @Getter
    private final NewOrderCancelCheckStatus newOrderCancelCheckStatusValue;

    // 新規申込MNP転出兼キャンセル可否確認
    @Getter
    private final MnpOutAndNewOrderCancelCheckStatus mnpOutAndNewOrderCancelCheckStatusValue;

    // MNP転出申込可否確認
    @Getter
    private final MnpOutApplyCheckStatus mnpOutApplyCheckStatusValue;

    // MNP転出申込可否確認
    @Getter
    private final MnpOutApplyCancelCheckStatus mnpOutApplyCancelCheckStatusValue;

    @Override
    public String getDbValue() {
        return dbValue.getNoRefactoringValue();
    }

    @Override
    public String getApiValue() {
        return apiValue.getNoRefactoringValue();
    }

    public String getReferApiValue() {
        return referApiValue.getNoRefactoringValue();
    }

    /**
     * 即時解約API
     * @return
     */
    public String getImmediateDisengagementReferApiValue() {
        return this.immediateDisengagementApiValue.getNoRefactoringValue();
    }

    /**
     * 解約確認
     * @return
     */
    public DisengagementCheckStatus getDisengagementCheckStatus() {
        return this.getDisengagementCheckStatusValue();
    }

    /**
     * MNP転出兼解約確認
     * @return
     */
    public MnpOutAndDisengagementCheckStatus getMnpOutAndDisengagementCheckStatus() {
        return this.getMnpOutAndDisengagementCheckStatusValue();
    }

    /**
     * 新規申込キャンセル
     * @return
     */
    public NewOrderCancelCheckStatus getNewOrderCancelReferStatus() {
        return this.getNewOrderCancelCheckStatusValue();
    }

    /**
     * MNP転出兼新規申込キャンセル
     * @return
     */
    public MnpOutAndNewOrderCancelCheckStatus getNewOrderMnpOutAndCancelCheckStatus() {
        return this.getMnpOutAndNewOrderCancelCheckStatusValue();
    }

    /**
     * MNP転出申込可否
     * @return
     */
    public MnpOutApplyCheckStatus getMnpOutApplyCheckStatus() {
        return this.getMnpOutApplyCheckStatusValue();
    }

    /**
     * MNP転出申込キャンセル可否
     * @return
     */
    public MnpOutApplyCancelCheckStatus getMnpOutApplyCancelCheckStatus() {
        return this.getMnpOutApplyCancelCheckStatusValue();
    }

    public String getReferApiValueStatus(){
        return getReferApiValue();
    }

    /**
     * DBに格納される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum MnpOutStatusDbValue implements DbValue {
        NO_REQUEST("init" /* 文字列リテラルの変更禁止 */),
        REQUEST_WAITING("request_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_RESERVATION_NUMBER_WAITING("mnp_reservation_number_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_WAITING("mnp_out_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_COMPLETION("mnp_out_completion" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_CANCEL("mnp_out_cancel" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * 契約情報一覧参照APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum EngagementDetailApiValue implements ApiValue {
        NO_REQUEST("no_request" /* 文字列リテラルの変更禁止 */),
        REQUEST_WAITING("request_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_RESERVATION_NUMBER_WAITING("mnp_reservation_number_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_WAITING("mnp_out_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_COMPLETION("mnp_out_completion" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_CANCEL("mnp_out_cancel" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * 契約情報一覧参照APIで返却される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum ReferApiValue implements ApiValue {
        EXIST(StereotypedCharacters.EXIST /* 文字列リテラルの変更禁止 */),
        NOT_EXIST(StereotypedCharacters.NOT_EXIST /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }

    /**
     * 即時キャンセルAPIとして公開される値（文字列リテラル）の定義
     */
    @AllArgsConstructor
    private enum ImmediateDisengagementApiValue implements ApiValue {
        MNP_OUT_PROCESSING("mnp_out_processing" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_WAITING("mnp_out_waiting" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_COMPLETION("mnp_out_completion" /* 文字列リテラルの変更禁止 */),
        MNP_OUT_NOT_ORDERED("mnp_out_not_ordered" /* 文字列リテラルの変更禁止 */);

        @Getter
        private final String noRefactoringValue;
    }
}
