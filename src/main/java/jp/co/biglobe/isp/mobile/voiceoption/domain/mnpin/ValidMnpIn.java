package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.extension.domain.CommonValidEntity;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.ValidMnpPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MsisdnDoubleRegistrationCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidMnpIn implements MnpIn, CommonValidEntity<ValidMnpIn> {

    @Getter
    private final VoiceEngagementNumber voiceEngagementNumber;

    @Getter
    private final VoiceMsisdn voiceMsisdn;

    @Getter
    private final MnpReservationNumber mnpReservationNumber;

    @Getter
    private final MnpInPersonalInfo mnpInPersonalInfo;

    @Getter
    private final MnpInActivation mnpInActivation;

    public ValidMnpIn(
            VoiceEngagementNumber voiceEngagementNumber,
            VoiceMsisdn voiceMsisdn,
            MnpReservationNumber mnpReservationNumber,
            ValidMnpInPersonalInfo validMnpInPersonalInfo,
            ValidMnpInActivation validMnpInActivation) {
        this.voiceEngagementNumber = voiceEngagementNumber;
        this.voiceMsisdn = voiceMsisdn;
        this.mnpReservationNumber = mnpReservationNumber;
        if (validMnpInPersonalInfo == null) {
            this.mnpInPersonalInfo = new NotExistMnpInPersonalInfo();
        } else {
            this.mnpInPersonalInfo = validMnpInPersonalInfo;
        }
        if (validMnpInActivation == null) {
            this.mnpInActivation = new NotExistMnpInActivation();
        } else {
            this.mnpInActivation = validMnpInActivation;
        }
    }

    public ValidMnpIn(VoiceEngagementNumber voiceEngagementNumber, MnpInInitialRequestData mnpInInitialRequestData) {
        this.voiceEngagementNumber = voiceEngagementNumber;
        this.voiceMsisdn = mnpInInitialRequestData.getVoiceMsisdn();
        this.mnpReservationNumber = mnpInInitialRequestData.getMnpReservationNumber();
        if (mnpInInitialRequestData.getMnpInPersonalInfo().isExist()) {
            this.mnpInPersonalInfo = new ValidMnpInPersonalInfo(voiceEngagementNumber, (ValidMnpPersonalInfo) mnpInInitialRequestData.getMnpInPersonalInfo());
        } else {
            this.mnpInPersonalInfo = new NotExistMnpInPersonalInfo();
        }
        this.mnpInActivation = new NotExistMnpInActivation();
    }

    @Override
    public boolean isIdentificationUploadDeadlineOver(VoiceEngagement voiceEngagement) {
        return voiceEngagement.isIdentificationUploadDeadlineOverForMnpIn();
    }

    public ValidMnpIn inputMnpInPersonalItems(ValidMnpPersonalInfo validMnpPersonalInfo) {
        return new ValidMnpIn(
                this.voiceEngagementNumber,
                this.voiceMsisdn,
                this.mnpReservationNumber,
                new ValidMnpInPersonalInfo(this.voiceEngagementNumber, validMnpPersonalInfo),
                this.mnpInActivation);
    }

    public ValidMnpIn inputActivationDate(ValidMnpInActivationDueDate validMnpInActivationDueDate, ValidIdentification validIdentification){
        if (validIdentification.getIdentificationStatus() != IdentificationStatus.OK) {
            throw new SystemCheckException("本人確認が未完了です", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        /**
         * 住所の不備などによりSIMが着荷せず、本人確認をやり直した場合はすでにアクティベーション予定日が
         * 設定されているため、新たにアクティベーション予定日を設定する事はしない。
         */
        if (this.mnpInActivation.isExist()) {
            return this;
        }

        ValidMnpInActivation validMnpInActivation = new ValidMnpInActivation(this.voiceEngagementNumber, validMnpInActivationDueDate);
        return new ValidMnpIn(voiceEngagementNumber,voiceMsisdn,mnpReservationNumber,mnpInPersonalInfo, validMnpInActivation);
    }

    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public boolean isNotExist() { return false; }

    @Override
    public MsisdnDoubleRegistrationCheckStatus verifyMsisdnDoubleRegistration() {
        return MsisdnDoubleRegistrationCheckStatus.REGISTERED;
    }

    public String getMnpActivationDueDateForApiValue() {
        return this.mnpInActivation.getMnpActivationDueDateForApiValue();
    }

    public boolean equalsMnpInParentInfo(ValidMnpIn o){

        return (this.voiceEngagementNumber.equals(o.getVoiceEngagementNumber())
                && this.voiceMsisdn.equals(o.getVoiceMsisdn())
                && this.mnpReservationNumber.equals(o.getMnpReservationNumber()));

    }

    public MnpOutMsisdn getMnpOutMsisdn() {
        return new MnpOutMsisdn(getVoiceMsisdn().getApiValue());
    }

    /**
     * データ変更時に、Update でなく、Delete & Insert する必要があるか、判定する（外部参照キーが違えば true）
     */
    @Override
    public boolean requiredDeleteAndInsert(ValidMnpIn validEntity) {
        // 外部参照キーが変わっているか判定する
        return !this.voiceEngagementNumber.equals(validEntity.getVoiceEngagementNumber());
    }

    /**
     * 子エンティティを除く値が同じか
     */
    @Override
    public boolean equalsExcludeChild(ValidMnpIn validEntity) {
        ValidMnpIn x = new ValidMnpIn(
                this.voiceEngagementNumber,
                this.voiceMsisdn,
                this.mnpReservationNumber,
                new NotExistMnpInPersonalInfo(), // 子エンティティは空にする
                new NotExistMnpInActivation()); // 子エンティティは空にする
        ValidMnpIn y = new ValidMnpIn(
                validEntity.getVoiceEngagementNumber(),
                validEntity.getVoiceMsisdn(),
                validEntity.getMnpReservationNumber(),
                new NotExistMnpInPersonalInfo(), // 子エンティティは空にする
                new NotExistMnpInActivation()); // 子エンティティは空にする
        return x.equals(y);
    }
}
