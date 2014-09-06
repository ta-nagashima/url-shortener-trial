package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletionConfirmedDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.NotExistMnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.ValidMnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.MnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.NullMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.joda.time.DateTime;


public class ValidMnpOutBuilder {
    private VoiceMnpOutId voiceMnpOutId = new VoiceMnpOutId(1);
    private VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(1);
    private MnpOutCompletion mnpOutCompletion = new NotExistMnpOutCompletion();
    private MnpOutStatus mnpOutStatus = MnpOutStatus.REQUEST_WAITING;
    private MnpOutMsisdn mnpOutMsisdn = new MnpOutMsisdn("090-1234-5678");
    private MnpOutCancelReason mnpOutCancelReason = MnpOutCancelReason.NOT_CANCEL;
    private MnpOutReservationNumber mnpOutReservationNumber = new NullMnpOutReservationNumber();
    private ValidMnpOutPersonalInfo validMnpOutPersonalInfo = new ValidMnpOutPersonalInfo(
            voiceMnpOutId,
            new MnpFullName("小池　直樹"),
            new MnpFullNameKana("ｺｲｹ ﾅｵｷ"),
            MnpGender.MALE,
            new MnpBirthday("19840309")
    );

    // todo : とりあえずテストが通るように妥協した…テスト方法から検討

    public ValidMnpOutBuilder voiceMnpOutId(Integer i) {
        this.voiceMnpOutId = new VoiceMnpOutId(i);

        this.validMnpOutPersonalInfo = new ValidMnpOutPersonalInfo(
                voiceMnpOutId,
                new MnpFullName("小池　直樹"),
                new MnpFullNameKana("ｺｲｹ ﾅｵｷ"),
                MnpGender.MALE,
                new MnpBirthday("19840309")
        );

        return this;
    }


    public ValidMnpOutBuilder voiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber) {
        this.voiceEngagementNumber = voiceEngagementNumber;
        return this;
    }

    public ValidMnpOutBuilder voiceEngagementNumber(Integer i) {
        VoiceEngagementNumber newVoiceEngagementNumber = new VoiceEngagementNumber(i);
        this.voiceEngagementNumber(newVoiceEngagementNumber);
        return this;
    }


    public ValidMnpOutBuilder mnpOutDate(MnpOutCompletionConfirmedDate mnpOutCompletionConfirmedDate) {
        this.mnpOutCompletion = new ValidMnpOutCompletion(voiceMnpOutId, mnpOutCompletionConfirmedDate);
        return this;
    }

    public ValidMnpOutBuilder mnpOutDate(DateTime dateTime) {
        this.mnpOutCompletion = new ValidMnpOutCompletion(voiceMnpOutId, new MnpOutCompletionConfirmedDate(dateTime.toString("yyyyMMdd")));
        return this;
    }



    public ValidMnpOutBuilder mnpOutStatus(MnpOutStatus mnpOutStatus) {
        this.mnpOutStatus = mnpOutStatus;
        return this;
    }

    public ValidMnpOutBuilder mnpOutReservationNumber(MnpOutReservationNumber mnpOutReservationNumber) {
        this.mnpOutReservationNumber = mnpOutReservationNumber;
        return this;
    }

    public ValidMnpOutBuilder mnpOutReservationNumber() {
        this.mnpOutReservationNumber = new ValidMnpOutReservationNumber(
                this.voiceMnpOutId,
                new MnpReservationNumber("11-222-33333"),
                new ExpireDate("20140415"),
                new ExecutionDate("20140401"),
                new OperatorId("abc12345")
        );
        return this;
    }

    public ValidMnpOutBuilder mnpOutReservationNumber(DateTime dt) {
        this.mnpOutReservationNumber = new ValidMnpOutReservationNumber(
                this.voiceMnpOutId,
                new MnpReservationNumber("11-222-33333"),
                new ExpireDate(dt.toString("yyyyMMdd")),
                new ExecutionDate(dt.minusDays(14).toString("yyyyMMdd")),
                new OperatorId("abc12345")
        );
        return this;
    }

    public ValidMnpOutBuilder mnpOutCancelReason(MnpOutCancelReason mnpOutCancelReason) {
        this.mnpOutCancelReason = mnpOutCancelReason;
        return this;
    }

    public ValidMnpOutBuilder mnpInputItems(ValidMnpOutPersonalInfo validMnpOutPersonalInfo){
        this.validMnpOutPersonalInfo = validMnpOutPersonalInfo;
        return this;
    }

    public ValidMnpOutBuilder mnpInputItems(String name, String kana, MnpGender mnpGender, String birthDay){
        this.mnpInputItems(new ValidMnpOutPersonalInfo(
                voiceMnpOutId,
                new MnpFullName(name),
                new MnpFullNameKana(kana),
                mnpGender,
                new MnpBirthday(birthDay)
        ));
        return this;
    }


    public ValidMnpOut build() {
        return new ValidMnpOut(
                voiceMnpOutId,
                voiceEngagementNumber,
                mnpOutCompletion,
                mnpOutStatus,
                mnpOutMsisdn,
                mnpOutCancelReason,
                mnpOutReservationNumber,
                validMnpOutPersonalInfo);
    }
}
