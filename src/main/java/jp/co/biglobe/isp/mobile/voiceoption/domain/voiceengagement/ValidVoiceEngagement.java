package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import jp.co.biglobe.lib.publication.date.ForeverDate;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.exception.VoiceEngagementInvalidStatusException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;


@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVoiceEngagement implements VoiceEngagement, CommonValidEntity<ValidVoiceEngagement> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    @Getter
    private final VoiceEngagementStatus voiceEngagementStatus;

    @Getter
    private final VoiceEngagementStartDate voiceEngagementStartDate;

    @Getter
    private final VoiceEngagementEndDateTime voiceEngagementEndDateTime;

    @Getter
    private final VoiceEngagementCancel voiceEngagementCancel;

    @Getter
    private final VoiceEngagementDisengage voiceEngagementDisengage;

    @Getter
    private final VoiceEngagementLinkage voiceEngagementLinkage;

    @Getter
    private final NewOrderInfo newOrderInfo;

    @Getter
    private final CancelListOutPutStatus cancelListOutPutStatus;

    @Getter
    private final VoiceOrderType voiceOrderType;

    public ValidVoiceEngagement(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementInitialRequestData voiceEngagementInitialRequestData,
            VoiceOrderType voiceOrderType) {

        ForeverDate foreverDate = new ForeverDate();

        this.voiceEngagementNumber = voiceEngagementNumber;
        this.voiceEngagementStatus = VoiceEngagementEvent.ORDER.getToStatus();
        this.voiceEngagementStartDate = new VoiceEngagementStartDate(foreverDate.get_yyyyMMdd());
        this.voiceEngagementEndDateTime = new VoiceEngagementEndDateTime(foreverDate.getDate());
        this.voiceEngagementCancel = new NotExistVoiceEngagementCancel();
        this.voiceEngagementDisengage = new NotExistVoiceEngagementDisengage();
        this.voiceEngagementLinkage = new VoiceEngagementLinkage(voiceEngagementInitialRequestData);
        this.newOrderInfo = new NewOrderInfo(voiceEngagementInitialRequestData);
        this.cancelListOutPutStatus = CancelListOutPutStatus.NOT_OUTPUT;
        this.voiceOrderType = voiceOrderType;

    }

    public ValidVoiceEngagement(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceEngagementStatus voiceEngagementStatus,
            VoiceEngagementStartDate voiceEngagementStartDate,
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            ValidVoiceEngagementCancel validVoiceEngagementCancel,
            ValidVoiceEngagementDisengage validVoiceEngagementDisengage,
            VoiceEngagementLinkage voiceEngagementLinkage,
            NewOrderInfo newOrderInfo,
            CancelListOutPutStatus cancelListOutPutStatus,
            VoiceOrderType voiceOrderType) {

        this.voiceEngagementNumber = voiceEngagementNumber;
        this.voiceEngagementStatus = voiceEngagementStatus;
        this.voiceEngagementStartDate = voiceEngagementStartDate;
        this.voiceEngagementEndDateTime = voiceEngagementEndDateTime;
        this.voiceEngagementCancel = verifyVoiceEngagementCancel(validVoiceEngagementCancel);
        this.voiceEngagementDisengage = verifyVoiceEngagementDisengage(validVoiceEngagementDisengage);
        this.voiceEngagementLinkage = voiceEngagementLinkage;
        this.newOrderInfo = newOrderInfo;
        this.cancelListOutPutStatus = cancelListOutPutStatus;
        this.voiceOrderType = voiceOrderType;
    }

    private VoiceEngagementCancel verifyVoiceEngagementCancel(ValidVoiceEngagementCancel validVoiceEngagementCancel) {
        if (validVoiceEngagementCancel == null) {
            return new NotExistVoiceEngagementCancel();
        }
        return validVoiceEngagementCancel;
    }

    private VoiceEngagementDisengage verifyVoiceEngagementDisengage(ValidVoiceEngagementDisengage validVoiceEngagementDisengage) {
        if (validVoiceEngagementDisengage == null) {
            return new NotExistVoiceEngagementDisengage();
        }
        return validVoiceEngagementDisengage;
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() {
        return false;
    }

    @Override
    public boolean notCanIdentificationUploadStatus() {
        return !voiceEngagementStatus.canDocumentUploadStatus();
    }

    @Override
    public boolean isIdentificationUploadDeadlineOverForMnpIn() {
        return newOrderInfo.isIdentificationUploadDeadlineOverForMnpIn();
    }

    private boolean isVoiceEngagementOrderDeadlineOver(ValidIdentification validIdentification) {

        if (!validIdentification.isEmptyExcutionDate()) {
            return validIdentification.isVoiceEngagementOrderDeadlineOver();
        }
        return newOrderInfo.isVoiceEngagementOrderDeadlineOver();
    }

    private ValidVoiceEngagement createValidVoiceEngagement(
            VoiceEngagementStatus voiceEngagementStatus,
            VoiceEngagementStartDate voiceEngagementStartDate,
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            VoiceEngagementCancel voiceEngagementCancel,
            VoiceEngagementDisengage voiceEngagementDisengage,
            VoiceEngagementLinkage voiceEngagementLinkage) {

        return new ValidVoiceEngagement(
                this.voiceEngagementNumber,
                voiceEngagementStatus,
                voiceEngagementStartDate,
                voiceEngagementEndDateTime,
                voiceEngagementCancel,
                voiceEngagementDisengage,
                voiceEngagementLinkage,
                newOrderInfo,
                this.cancelListOutPutStatus,
                this.voiceOrderType
        );
    }

    public ValidVoiceEngagement engaged(VoiceEngagementStartDate voiceEngagementStartDate) {
        if (VoiceEngagementEvent.ENGAGE.isNgFromStatus(voiceEngagementStatus)) {
            throw new SystemCheckException("音声オプション契約のステータスが異常", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return createValidVoiceEngagement(
                VoiceEngagementEvent.ENGAGE.getToStatus(),
                voiceEngagementStartDate,
                this.voiceEngagementEndDateTime,
                this.voiceEngagementCancel,
                this.voiceEngagementDisengage,
                voiceEngagementLinkage);
    }


    public ValidVoiceEngagement disengageCancel(Date requestDate) {
        if (VoiceEngagementEvent.DISENGAGE_CANCEL.isNgFromStatus(voiceEngagementStatus) || isDisengaged(requestDate)) {
            throw new SystemCheckException("音声オプション契約のステータスが異常", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        ForeverDate foreverDate = new ForeverDate();
        return createValidVoiceEngagement(
                VoiceEngagementEvent.ENGAGE.getToStatus(),
                this.voiceEngagementStartDate,
                new VoiceEngagementEndDateTime(foreverDate.getDate()),
                this.voiceEngagementCancel,
                new NotExistVoiceEngagementDisengage(),
                voiceEngagementLinkage);
    }


    private ValidVoiceEngagement cancel(
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate,
            MnpOut mnpOut) {
        if (VoiceEngagementEvent.CANCEL.isNgFromStatus(voiceEngagementStatus)) {
            throw new SystemCheckException("音声オプション契約のステータスが異常", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        if (mnpOut.getMnpOutStatus() != MnpOutStatus.NO_REQUEST) {
            if (mnpOut.canNotVoiceEngageCancel()) {
                throw new SystemCheckException("MNP転出状態が不正のため、申込できません", VoiceOptionAlarmIdentifier.DEFAULT);
            }
        }

        return createValidVoiceEngagement(
                VoiceEngagementEvent.CANCEL.getToStatus(),
                this.voiceEngagementStartDate,
                this.voiceEngagementEndDateTime,
                this.voiceEngagementCancel.cancel(this.voiceEngagementNumber, voiceEngagementCancelReason, voiceEngagementCancelOrderDate),
                this.voiceEngagementDisengage,
                voiceEngagementLinkage);
    }

    public ValidVoiceEngagement cancelWithMnpOutComplete(VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate, ValidMnpOut validMnpOut) {
        return cancel(VoiceEngagementCancelReason.USER_REQUEST, voiceEngagementCancelOrderDate, validMnpOut);
    }

    public ValidVoiceEngagement cancelWithNoMnpOut(
            VoiceEngagementCancelReason voiceEngagementCancelReason,
            VoiceEngagementCancelOrderDate voiceEngagementCancelOrderDate,
            MnpOut mnpOut) {

        // 即時キャンセルの場合、転出完了状態で呼ばれることがないので、エラーとする
        // この場合は、転出完了兼新規申込キャンセルが呼ばれる。
        if (mnpOut.getMnpOutStatus() != MnpOutStatus.NO_REQUEST) {
            if (mnpOut.isCompletion()) {
                throw new SystemCheckException("MNP転出状態が不正のため、申込できません", VoiceOptionAlarmIdentifier.DEFAULT);
            }
        }

        return cancel(voiceEngagementCancelReason, voiceEngagementCancelOrderDate, mnpOut);
    }

    private ValidVoiceEngagement disengage(
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            VoiceEngagementDisengageReason voiceEngagementDisengageReason,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate,
            MnpOut mnpOut) {

        if (VoiceEngagementEvent.DISENGAGE.isNgFromStatus(voiceEngagementStatus)) {
            throw new SystemCheckException("音声オプション契約のステータスが異常", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        if (mnpOut.verifyDisengagementCheckStatus().isNg()) {
            throw new SystemCheckException("MNP転出状態が不正のため、申込できません", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return createValidVoiceEngagement(
                VoiceEngagementEvent.DISENGAGE.getToStatus(),
                this.voiceEngagementStartDate,
                voiceEngagementEndDateTime,
                this.voiceEngagementCancel,
                this.voiceEngagementDisengage.disengage(this.voiceEngagementNumber, voiceEngagementDisengageReason, voiceEngagementDisengageOrderDate),
                voiceEngagementLinkage
        );

    }

    public ValidVoiceEngagement disengageWithMnpOutComplete(
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate,
            ValidMnpOut validMnpOut) {

        return disengage(
                voiceEngagementEndDateTime, VoiceEngagementDisengageReason.USER_REQUEST,
                voiceEngagementDisengageOrderDate, validMnpOut
        );

    }

    public ValidVoiceEngagement disengageWithNoMnpOut(
            VoiceEngagementEndDateTime voiceEngagementEndDateTime,
            VoiceEngagementDisengageReason voiceEngagementDisengageReason,
            VoiceEngagementDisengageOrderDate voiceEngagementDisengageOrderDate,
            MnpOut mnpOut) {

        // 解約の場合、転出完了状態で呼ばれることがないので、エラーとする
        // この場合は、転出完了兼解約が呼ばれる。
        if (mnpOut.isCompletion()) {
            throw new SystemCheckException("MNP転出状態が不正のため、申込できません", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return disengage(
                voiceEngagementEndDateTime, voiceEngagementDisengageReason,
                voiceEngagementDisengageOrderDate, mnpOut
        );

    }

    public ValidVoiceEngagement changeEquipmentNumber(EquipmentNumber newNumber, MnpOut mnpOut) {

        if (verifySimChange(mnpOut).isNg()) {
            throw new SystemCheckException("Simが変更できないステータス", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return createValidVoiceEngagement(
                this.voiceEngagementStatus,
                this.voiceEngagementStartDate,
                this.voiceEngagementEndDateTime,
                this.voiceEngagementCancel,
                this.voiceEngagementDisengage,
                this.voiceEngagementLinkage.simChange(newNumber));

    }

    @Override
    public DisengagementCheckStatus verifyDisengagementCheckStatus(MnpOut mnpOut) {

        if (VoiceEngagementEvent.DISENGAGE.isNgFromStatus(voiceEngagementStatus)) {
            return DisengagementCheckStatus.NO_VOICE_OPTION;
        }

        return mnpOut.verifyDisengagementCheckStatus();

    }

    @Override
    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus(MnpOut mnpOut) {

        if (VoiceEngagementEvent.DISENGAGE.isNgFromStatus(voiceEngagementStatus)) {
            return MnpOutAndDisengagementCheckStatus.NO_VOICE_OPTION;
        }

        return mnpOut.verifyMnpOutAndDisengagementCheckStatus();

    }

    @Override
    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut) {

        // 音声契約が申し込み中でない場合
        if (VoiceEngagementEvent.CANCEL.isNgFromStatus(voiceEngagementStatus)) {
            return NewOrderCancelCheckStatus.NO_VOICE_OPTION;
        }

        // NMP転入でない場合
        if (mnpIn.isNotExist()) {
            return NewOrderCancelCheckStatus.NO_MNP_IN;
        }

        // MNP転出状態の確認が必要な場合
        return mnpOut.verifyNewOrderCancelCheckStatus();

    }

    @Override
    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus(MnpIn mnpIn, MnpOut mnpOut) {

        // 音声契約が申し込み中でない場合
        if (VoiceEngagementEvent.CANCEL.isNgFromStatus(voiceEngagementStatus)) {
            return MnpOutAndNewOrderCancelCheckStatus.NO_VOICE_OPTION;
        }

        // NMP転入でない場合
        if (mnpIn.isNotExist()) {
            return MnpOutAndNewOrderCancelCheckStatus.NO_MNP_IN;
        }

        // MNP転出状態の確認が必要な場合
        return mnpOut.verifyNewOrderMnpOutAndCancelCheckStatus();

    }

    @Override
    public SimChangeCheckStatus verifySimChange(MnpOut mnpOut) {
        return mnpOut.verifySimSizeChangeCheckStatus();
    }

    @Override
    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut) {

        // 音声契約が申込中または契約中でない場合
        if (VoiceEngagementEvent.MNP_OUT.isNgFromStatus(voiceEngagementStatus)) {
            return MnpOutApplyCheckStatus.NO_VOICE_OPTION;
        }

        // 音声契約が申込中かつMNP転入がない場合
        if (voiceEngagementStatus == VoiceEngagementStatus.ORDERED && mnpIn.isNotExist()) {
            return MnpOutApplyCheckStatus.NO_MNP_IN;
        }

        // 本人確認が未完了の場合
        if (identification.isNotIdentificationStatusOK()) {
            return MnpOutApplyCheckStatus.NO_IDENTIFICATION;
        }

        // MNP転出状態の確認が必要な場合
        return mnpOut.verifyMnpOutApplyCheckStatus();

    }

    @Override
    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus(Identification identification, MnpIn mnpIn, MnpOut mnpOut) {

        // 音声契約が申し込み中でない場合
        if (VoiceEngagementEvent.MNP_OUT.isNgFromStatus(voiceEngagementStatus)) {
            return MnpOutApplyCancelCheckStatus.NO_VOICE_OPTION;
        }

        // 音声契約が申込中かつMNP転入がない場合
        if (voiceEngagementStatus == VoiceEngagementStatus.ORDERED && mnpIn.isNotExist()) {
            return MnpOutApplyCancelCheckStatus.NO_MNP_IN;
        }

        // 本人確認が未完了の場合
        if (identification.isNotIdentificationStatusOK()) {
            return MnpOutApplyCancelCheckStatus.NO_IDENTIFICATION;
        }

        // MNP転出状態の確認が必要な場合
        return mnpOut.verifyMnpOutApplyCancelCheckStatus();

    }

    /**
     * キャンセル・解約時にMnp転出の意思確認が必要かどうかを返却する。
     */
    @Override
    public boolean isNecessaryToMnpOutIntentionCheck(MnpIn mnpIn, MnpOut mnpOut) {

        // 音声オプション申し込み中でMnp転入があり、なおかつ有効なMnp転出がないときにtrue
        if(this.voiceEngagementStatus.equals(VoiceEngagementStatus.ORDERED) && mnpIn.isExist() && !mnpOut.isValidMnpOut()) {
            return true;
        }

        // 音声オプションが契約中で有効なMnp転出がないときにtrue
        if(this.voiceEngagementStatus.equals(VoiceEngagementStatus.ENGAGED) && !mnpOut.isValidMnpOut()){
            return true;
        }

        // それ以外のときfalse
        return false;
    }

    private boolean isDisengageReserved(Date date) {
        return this.voiceEngagementStatus == VoiceEngagementStatus.DISENGAGED
                && (this.voiceEngagementEndDateTime.isAfter(date) || this.voiceEngagementEndDateTime.isEqual(date));
    }


    private boolean isDisengaged(Date date) {
        return this.voiceEngagementStatus == VoiceEngagementStatus.DISENGAGED
                && (this.voiceEngagementEndDateTime.isBefore(date));
    }

    public boolean isExistVoiceEngagementCancel() {
        return this.voiceEngagementCancel.isExist();
    }

    public boolean isExistVoiceEngagementDisengage() {
        return this.voiceEngagementDisengage.isExist();
    }

    public boolean equalsVoiceEngagementDisengage(VoiceEngagementDisengage voiceEngagementDisengage) {
        return this.voiceEngagementDisengage.equals(voiceEngagementDisengage);
    }

    public ValidVoiceEngagement outputCancelList(ValidIdentification validIdentification) {

        if (VoiceEngagementEvent.CANCEL.isNgFromStatus(voiceEngagementStatus)) {
            throw new VoiceEngagementInvalidStatusException("契約状態が不正のため、事務局へのキャンセル依頼リストへの出力ができません");
        }

        if (!validIdentification.isCanOutputCancelList()) {
            throw new VoiceEngagementInvalidStatusException("本人確認状態が不正のため、事務局へのキャンセル依頼リストへの出力ができません");
        }

        if (!this.cancelListOutPutStatus.isCanOutputCancelList()) {
            throw new VoiceEngagementInvalidStatusException("既にキャンセル依頼リストに出力済みのため、事務局へのキャンセル依頼リストへの出力ができません");
        }

        if (!isVoiceEngagementOrderDeadlineOver(validIdentification)) {
            throw new VoiceEngagementInvalidStatusException("申し込みが有効期限内のため、事務局へのキャンセル依頼リストへの出力ができません");
        }

        return new ValidVoiceEngagement(
                this.voiceEngagementNumber,
                this.voiceEngagementStatus,
                this.voiceEngagementStartDate,
                this.voiceEngagementEndDateTime,
                this.voiceEngagementCancel,
                this.voiceEngagementDisengage,
                this.voiceEngagementLinkage,
                this.newOrderInfo,
                CancelListOutPutStatus.OUTPUT,
                this.voiceOrderType
        );
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidVoiceEngagement validVoiceEngagement) {
        // 主キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validVoiceEngagement.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidVoiceEngagement validEntity) {
        ValidVoiceEngagement x = new ValidVoiceEngagement(
                this.voiceEngagementNumber,
                this.voiceEngagementStatus,
                this.voiceEngagementStartDate,
                this.voiceEngagementEndDateTime,
                new NotExistVoiceEngagementCancel(), // 子エンティティは空にする
                new NotExistVoiceEngagementDisengage(), // 子エンティティは空にする
                this.voiceEngagementLinkage,
                this.newOrderInfo,
                this.cancelListOutPutStatus,
                this.voiceOrderType);
        ValidVoiceEngagement y = new ValidVoiceEngagement(
                validEntity.getVoiceEngagementNumber(),
                validEntity.getVoiceEngagementStatus(),
                validEntity.getVoiceEngagementStartDate(),
                validEntity.getVoiceEngagementEndDateTime(),
                new NotExistVoiceEngagementCancel(), // 子エンティティは空にする
                new NotExistVoiceEngagementDisengage(), // 子エンティティは空にする
                validEntity.getVoiceEngagementLinkage(),
                validEntity.getNewOrderInfo(),
                validEntity.getCancelListOutPutStatus(),
                validEntity.getVoiceOrderType());
        return x.equals(y);
    }
}
