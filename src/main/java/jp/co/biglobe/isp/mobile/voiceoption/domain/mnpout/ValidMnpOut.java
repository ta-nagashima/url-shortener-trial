package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.exception.MnpOutInvalidStatusException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.NotExistMnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.ValidMnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.MnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.NullMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpOut implements MnpOut, CommonValidEntity<ValidMnpOut> {

    @Getter
    private final VoiceMnpOutId voiceMnpOutId;

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    @Getter
    private final MnpOutCompletion mnpOutCompletion;

    private final MnpOutStatus mnpOutStatus;

    @Getter
    private final MnpOutMsisdn mnpOutMsisdn;

    @Getter
    private final MnpOutCancelReason mnpOutCancelReason;

    @Getter
    private final MnpOutReservationNumber mnpOutReservationNumber;

    @Getter
    private final ValidMnpOutPersonalInfo validMnpOutPersonalInfo;

    public ValidMnpOut(
            VoiceMnpOutId voiceMnpOutId,
            VoiceEngagementNumber voiceEngagementNumber,
            ValidMnpOutCompletion validMnpOutCompletion,
            MnpOutStatus mnpOutStatus,
            MnpOutMsisdn mnpOutMsisdn,
            MnpOutCancelReason mnpOutCancelReason,
            ValidMnpOutReservationNumber validMnpOutReservationNumber,
            ValidMnpOutPersonalInfo validMnpOutPersonalInfo) {

        this.voiceMnpOutId = voiceMnpOutId;
        this.voiceEngagementNumber = voiceEngagementNumber;
        this.mnpOutCompletion = verifyMnpOutCompletion(validMnpOutCompletion);
        this.mnpOutStatus = mnpOutStatus;
        this.mnpOutMsisdn = mnpOutMsisdn;
        this.mnpOutCancelReason = mnpOutCancelReason;
        this.mnpOutReservationNumber = verifyMnpOutReservationNumber(validMnpOutReservationNumber);
        this.validMnpOutPersonalInfo = validMnpOutPersonalInfo;
    }

    @Override
    public boolean isExist(){
        return true;
    }

    @Override
    public boolean isNotExist(){
        return false;
    }

    private MnpOutCompletion verifyMnpOutCompletion(ValidMnpOutCompletion validMnpOutCompletion) {
        return validMnpOutCompletion == null ? new NotExistMnpOutCompletion() : validMnpOutCompletion;
    }

    private MnpOutReservationNumber verifyMnpOutReservationNumber(ValidMnpOutReservationNumber validMnpOutReservationNumber) {
        return validMnpOutReservationNumber == null ? new NullMnpOutReservationNumber() : validMnpOutReservationNumber;
    }

    public ValidMnpOut(VoiceMnpOutId voiceMnpOutId, VoiceEngagementNumber voiceEngagementNumber, MnpOutMsisdn mnpOutMsisdn, ValidMnpOutPersonalInfo validMnpOutPersonalInfo) {
        this.voiceMnpOutId = voiceMnpOutId;
        this.voiceEngagementNumber = voiceEngagementNumber;
        this.mnpOutCompletion = new NotExistMnpOutCompletion();
        this.mnpOutStatus = MnpOutStatus.REQUEST_WAITING;
        this.mnpOutMsisdn = mnpOutMsisdn;
        this.mnpOutCancelReason = MnpOutCancelReason.NOT_CANCEL;
        this.mnpOutReservationNumber = new NullMnpOutReservationNumber();
        this.validMnpOutPersonalInfo = validMnpOutPersonalInfo;
    }

    /**
     * MnpOutのmnpOutReservationNumber、mnpOutCompletion、personalInfo以外が
     * 等しいかどうかを返却する。
     *
     */
    public boolean isEqualsParent(ValidMnpOut o){
        return (voiceEngagementNumber == o.getVoiceEngagementNumber()
        && mnpOutStatus == o.mnpOutStatus
        && mnpOutMsisdn == o.mnpOutMsisdn
        && mnpOutCancelReason == o.mnpOutCancelReason);

    }



    public ValidMnpOut cancel(MnpOutCancelReason mnpOutCancelReason) {
        if (MnpOutEvent.MNP_OUT_CANCEL.isNgFromStatus(mnpOutStatus)) {
            throw new SystemCheckException("MNP転出状態が不正のため、キャンセルできません", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidMnpOut(
                this.voiceMnpOutId,
                this.voiceEngagementNumber,
                this.mnpOutCompletion,
                MnpOutStatus.MNP_OUT_CANCEL,
                this.mnpOutMsisdn,
                mnpOutCancelReason,
                this.mnpOutReservationNumber,
                this.validMnpOutPersonalInfo
        );
    }

    public ValidMnpOut changeStatusRequested(MnpOut mnpOut) {
        if (MnpOutEvent.MNP_RESERVATION_NUMBER_REQUEST.isNgFromStatus(mnpOut.getMnpOutStatus())) {
            throw new MnpOutInvalidStatusException("MNP転出状態が不正のため、事務局へのMNP転出予約番号の発行依頼ができません");
        }

        return new ValidMnpOut(
                this.voiceMnpOutId,
                this.voiceEngagementNumber,
                this.mnpOutCompletion,
                MnpOutEvent.MNP_RESERVATION_NUMBER_REQUEST.getToStatus(),
                this.mnpOutMsisdn,
                this.mnpOutCancelReason,
                this.mnpOutReservationNumber,
                this.validMnpOutPersonalInfo
        );
    }

    /**
     * 有効なMNPOUT（転出手続き中or転出完了）かどうかを返却。
     */

    @Override
    public boolean isValidMnpOut() {

        /**
         * NO_REQUEST
         * MNP_OUT_CANCEL
         * のときはfalse
         */
        if(this.mnpOutStatus.equals(MnpOutStatus.MNP_OUT_CANCEL) || this.mnpOutStatus.equals(MnpOutStatus.NO_REQUEST)){
            return false;
        }

        return true;
    }

    public ValidMnpOut reservationNumberRegister(
            MnpReservationNumber mnpReservationNumber,
            ExpireDate expireDate,
            ExecutionDate executionDate,
            OperatorId operatorId
    ) {
        if (MnpOutEvent.MNP_RESERVATION_NUMBER_ISSUE.isNgFromStatus(mnpOutStatus)) {
            throw new SystemCheckException("MNP転出状態が不正のため、登録できません", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidMnpOut(
                this.voiceMnpOutId,
                this.voiceEngagementNumber,
                this.mnpOutCompletion,
                MnpOutStatus.MNP_OUT_WAITING,
                this.mnpOutMsisdn,
                this.mnpOutCancelReason,
                new ValidMnpOutReservationNumber(
                        this.voiceMnpOutId,
                        mnpReservationNumber,
                        expireDate,
                        executionDate,
                        operatorId
                ),
                this.validMnpOutPersonalInfo
        );
    }

    public ValidMnpOut complete(MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate) {
        if (MnpOutEvent.MNP_OUT_COMPLETE.isNgFromStatus(mnpOutStatus)) {
            throw new SystemCheckException("MNP転出状態が不正のため、転出完了ができません", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidMnpOut(
                this.voiceMnpOutId,
                this.voiceEngagementNumber,
                new ValidMnpOutCompletion(this.voiceMnpOutId, mnpOutCompletionConfirmedDate),
                MnpOutEvent.MNP_OUT_COMPLETE.getToStatus(),
                this.mnpOutMsisdn,
                this.mnpOutCancelReason,
                this.mnpOutReservationNumber,
                this.validMnpOutPersonalInfo
        );
    }

    public boolean hasMnpOutReservationNumber() {
        return this.mnpOutReservationNumber.hasNumber();
    }

    // SIMサイズ変更可否チェック
    @Override
    public SimChangeCheckStatus verifySimSizeChangeCheckStatus() {
        return mnpOutStatus.getSimChangeCheckStatus();
    }

    @Override
    public MnpOutStatus getMnpOutStatus() {
        return mnpOutStatus;
    }

    @Override
    public void verifyApply() {
        if (MnpOutEvent.MNP_OUT_REQUEST.isNgFromStatus(mnpOutStatus)) {
            throw new SystemCheckException("MNP転出状態が不正のため、申込できません", VoiceOptionAlarmIdentifier.DEFAULT);
        }
    }

    @Override
    public boolean canNotVoiceEngageCancel() {

        if (mnpOutStatus.equals(MnpOutStatus.MNP_OUT_CANCEL)) {
            return false;
        }

        if (mnpOutStatus.equals(MnpOutStatus.MNP_OUT_COMPLETION)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isCompletion() {
        return this.mnpOutStatus.equals(MnpOutStatus.MNP_OUT_COMPLETION);
    }

    @Override
    public DisengagementCheckStatus verifyDisengagementCheckStatus() {
        return this.mnpOutStatus.getDisengagementCheckStatus();
    }

    @Override
    public MnpOutAndDisengagementCheckStatus verifyMnpOutAndDisengagementCheckStatus() {
        return this.mnpOutStatus.getMnpOutAndDisengagementCheckStatus();
    }

    @Override
    public NewOrderCancelCheckStatus verifyNewOrderCancelCheckStatus() {
        return this.mnpOutStatus.getNewOrderCancelReferStatus();
    }

    @Override
    public MnpOutAndNewOrderCancelCheckStatus verifyNewOrderMnpOutAndCancelCheckStatus() {
        return this.mnpOutStatus.getNewOrderMnpOutAndCancelCheckStatus();
    }

    @Override
    public MnpOutApplyCheckStatus verifyMnpOutApplyCheckStatus() {
        return this.mnpOutStatus.getMnpOutApplyCheckStatus();
    }

    @Override
    public MnpOutApplyCancelCheckStatus verifyMnpOutApplyCancelCheckStatus() {
        return this.mnpOutStatus.getMnpOutApplyCancelCheckStatus();
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpOut validEntity) {
        // 主キーか外部参照キーが変わっているか判定する
        return !this.voiceMnpOutId.equals(validEntity.getVoiceMnpOutId()) ||
               !this.voiceEngagementNumber.equals(validEntity.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpOut validEntity) {
        ValidMnpOut x = new ValidMnpOut(
                this.voiceMnpOutId,
                this.voiceEngagementNumber,
                new NotExistMnpOutCompletion(),
                this.mnpOutStatus,
                this.mnpOutMsisdn,
                this.mnpOutCancelReason,
                new NullMnpOutReservationNumber(),
                null);
        ValidMnpOut y = new ValidMnpOut(
                validEntity.getVoiceMnpOutId(),
                validEntity.getVoiceEngagementNumber(),
                new NotExistMnpOutCompletion(),
                validEntity.getMnpOutStatus(),
                validEntity.getMnpOutMsisdn(),
                validEntity.getMnpOutCancelReason(),
                new NullMnpOutReservationNumber(),
                null);
        return x.equals(y);
    }
}
