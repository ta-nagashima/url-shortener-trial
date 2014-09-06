package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.IdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.NotExistIdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.ValidIdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.NotExistIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.UploadCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementOrderValidityTerm;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidIdentification implements Identification, CommonValidEntity<ValidIdentification> {
    @Getter
    private final IdentificationReceiptNumber identificationReceiptNumber;
    @Getter
    private final IdentificationStatus identificationStatus;
    @Getter
    private final IdentificationLinkage identificationLinkage;
    @Getter
    private final VoiceClerkRequestStatus voiceClerkRequestStatus;
    @Getter
    private final IdentificationUpload identificationUpload;
    @Getter
    private final IdentificationResult identificationResult;

    public static ValidIdentification create(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationInitialRequestData identificationInitialRequestData,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatus.DOCUMENT_WAITING,
                identificationInitialRequestData.getUserId(),
                identificationInitialRequestData.getLteThreeGEngagementNumber(),
                voiceEngagementNumber,
                identificationInitialRequestData.getReceiptStatus().getVoiceClerkRequestStatus()
        );
    }
    public static ValidIdentification createDocumentWaitingVoiceClerkRequestRequested(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatus.DOCUMENT_WAITING,
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary.getUserId(),
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary.getLteThreeGEngagementNumber(),
                voiceEngagementNumber,
                VoiceClerkRequestStatus.REQUESTED
        );
    }

    public static ValidIdentification createOKVoiceClerkRequestUnnecessary(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatus.OK,
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary.getUserId(),
                identificationInitialRequestDataForVoiceClerkRequestUnnecessary.getLteThreeGEngagementNumber(),
                voiceEngagementNumber,
                VoiceClerkRequestStatus.UNNECESSARY
        );
    }

    public static ValidIdentification createOKVoiceClerkRequestUnnecessary(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationInitialRequestData identificationInitialRequestData,
            VoiceEngagementNumber voiceEngagementNumber) {

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatus.OK,
                identificationInitialRequestData.getUserId(),
                identificationInitialRequestData.getLteThreeGEngagementNumber(),
                voiceEngagementNumber,
                VoiceClerkRequestStatus.UNNECESSARY
        );
    }

    private ValidIdentification(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationStatus identificationStatus,
            UserId userId,
            LteThreeGEngagementNumber lteThreeGEngagementNumber,
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceClerkRequestStatus voiceClerkRequestStatus) {

        this.identificationReceiptNumber = identificationReceiptNumber;
        this.identificationStatus = identificationStatus;
        this.identificationLinkage = new IdentificationLinkage(userId, lteThreeGEngagementNumber, voiceEngagementNumber);
        this.voiceClerkRequestStatus = voiceClerkRequestStatus;
        this.identificationUpload = new NotExistIdentificationUpload();
        this.identificationResult = new NotExistIdentificationResult();
    }

    public ValidIdentification(
            IdentificationReceiptNumber identificationReceiptNumber,
            IdentificationStatus identificationStatus,
            IdentificationLinkage identificationLinkage,
            VoiceClerkRequestStatus voiceClerkRequestStatus,
            ValidIdentificationUpload validIdentificationUpload,
            ValidIdentificationResult validIdentificationResult) {
        this.identificationReceiptNumber = identificationReceiptNumber;
        this.identificationStatus = identificationStatus;
        this.identificationLinkage = identificationLinkage;
        this.voiceClerkRequestStatus = voiceClerkRequestStatus;
        if (validIdentificationUpload == null) {
            this.identificationUpload = new NotExistIdentificationUpload();
        } else {
            this.identificationUpload = validIdentificationUpload;
        }
        if (validIdentificationResult == null) {
            this.identificationResult = new NotExistIdentificationResult();
        } else {
            this.identificationResult = validIdentificationResult;
        }
    }

    public VoiceEngagementNumber getVoiceEngagementNumber() {
        return identificationLinkage.getVoiceEngagementNumber();
    }

    @Override
    public boolean isNotIdentificationStatusOK() {
        return !this.identificationStatus.equals(IdentificationStatus.OK);
    }

    @Override
    public UploadCheckStatus verifyUpload() {
        // 本人確認状態のチェック
        if (!this.identificationStatus.isCanUpload()) {
            return UploadCheckStatus.UPLOAD_DISABLE_STATUS;
        }

        // 発送管理登録状態によるアップロード可否チェック
        if (!this.voiceClerkRequestStatus.isCanUpload()) {
            return UploadCheckStatus.ENGAGEMENT_IS_PROVISIONAL;
        }

        // アップロード回数のチェック
        if (this.identificationUpload.isOverLimitCount()) {
            return UploadCheckStatus.UPLOAD_LIMIT_COUNT_OVER;
        }

        return UploadCheckStatus.AVAILABLE;
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() {
        return false;
    }

    // アップロード実施後の本人確認 Entity
    public ValidIdentification uploaded() {
        return new ValidIdentification(
                this.identificationReceiptNumber,
                IdentificationStatusEvent.RECEIVE_DOCUMENT.getToStatus(),
                this.identificationLinkage,
                this.voiceClerkRequestStatus,
                this.identificationUpload.uploaded(this.identificationReceiptNumber),  // ←アップロードされた回数をカウントアップする
                this.identificationResult
        );
    }

    /**
     * todo : 検討
     * ７月３０日現在、仮受付復旧の処理で特に不正なステータスによるガードは入れていない。
     * どうするかは今後検討。
     */

    public ValidIdentification changeVoiceClerkRequest() {

        // 不正なステータスのときになにもしないを実現するために、あえて例外をとばさずに自分自身を返す
        if(VoiceClerkRequestStatusEvent.PROVISIONAL_RESUME.isNgFromStatus(voiceClerkRequestStatus)){

            return this;
        }

        return new ValidIdentification(
                this.identificationReceiptNumber,
                this.identificationStatus,
                this.identificationLinkage,
                VoiceClerkRequestStatusEvent.PROVISIONAL_RESUME.getToStatus(),
                this.identificationUpload,
                this.identificationResult
        );
    }

    public ValidIdentification reflectIdentificationNgResult(ValidIdentificationResult validIdentificationResult) {

        if(IdentificationStatusEvent.NG.isNgFromStatus(identificationStatus)){
            throw new SystemCheckException("本人確認の結果を反映できないステータスです。", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatusEvent.NG.getToStatus(),
                identificationLinkage,
                voiceClerkRequestStatus,
                this.identificationUpload.reset(),
                validIdentificationResult
        );
    }

    public ValidIdentification reflectIdentificationOkResult(ValidIdentificationResult validIdentificationResult) {

        if(IdentificationStatusEvent.OK.isNgFromStatus(identificationStatus)){
            throw new SystemCheckException("本人確認の結果を反映できないステータスです。", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidIdentification(
                identificationReceiptNumber,
                IdentificationStatusEvent.OK.getToStatus(),
                identificationLinkage,
                voiceClerkRequestStatus,
                identificationUpload,
                validIdentificationResult
        );
    }

    /**
     * 本人確認未実施キャンセルリストに出力可能かどうかを返す
     */
    public boolean isCanOutputCancelList(){
        return this.identificationStatus.isCanOutputCancelList();
    }


    public boolean isEmptyExcutionDate() {
        return this.identificationResult.getExcutionDateForApiValue().isEmpty();
    }

    /**
     * 本人確認NG後の場合のオーダー有効期限内かどうかを返却
     */
    public boolean isVoiceEngagementOrderDeadlineOver() {

        if (this.identificationResult.getExcutionDateForApiValue().isEmpty()) {
            return false;
        }
        DateTime nowDateTime = new DateTime();
        DateTime limitDateTime = getVoiceEngagementOrderValidDateTime();

        return isDeadlineOver(nowDateTime, limitDateTime);
    }

    private DateTime getVoiceEngagementOrderValidDateTime() {
        DateTime orderDateTime = getValueWithDateTime();
        return orderDateTime.plusDays(VoiceEngagementOrderValidityTerm.VALIDITY_TERM - 1).withTime(0, 0, 0, 0);
    }

    private boolean isDeadlineOver(DateTime nowDateTime, DateTime limitDateTime) {
        if (nowDateTime.isAfter(limitDateTime)) {
            return true;
        }
        return false;
    }

    private DateTime getValueWithDateTime() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(identificationResult.getExcutionDateForApiValue());

            return new DateTime(date);

        } catch (ParseException e) {
            throw new SystemCheckException("音声オプション契約申込日フォーマットエラー", VoiceOptionAlarmIdentifier.DEFAULT);
        }
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidIdentification validIdentification) {
        // 主キーか外部参照キーが変わっているか判定する
        return !this.identificationReceiptNumber.equals(validIdentification.getIdentificationReceiptNumber()) ||
               !this.identificationLinkage.getVoiceEngagementNumber().equals(validIdentification.getIdentificationLinkage().getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidIdentification validEntity) {
        ValidIdentification x = new ValidIdentification(
                this.identificationReceiptNumber,
                this.identificationStatus,
                this.identificationLinkage,
                this.voiceClerkRequestStatus,
                new NotExistIdentificationUpload(),
                new NotExistIdentificationResult());
        ValidIdentification y = new ValidIdentification(
                validEntity.getIdentificationReceiptNumber(),
                validEntity.getIdentificationStatus(),
                validEntity.getIdentificationLinkage(),
                validEntity.getVoiceClerkRequestStatus(),
                new NotExistIdentificationUpload(),
                new NotExistIdentificationResult());
        return x.equals(y);
    }
}
