package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public class ValidMnpInBuilder {

    private VoiceEngagementNumber voiceEngagementNumber = new VoiceEngagementNumber(new Integer(1));
    private VoiceMsisdn voiceMsisdn = new VoiceMsisdn("090-1234-5678");
    private MnpReservationNumber mnpReservationNumber = new MnpReservationNumber("00-111-12345");
    private MnpPersonalInfo mnpPersonalInfo = new ValidMnpPersonalInfo(
            new MnpFullName("テスト太郎"),
            new MnpFullNameKana("ﾃｽﾄ ﾀﾛｳ"),
            MnpGender.MALE,
            new MnpBirthday("20140510"));
    private MnpInActivation mnpInActivation = new NotExistMnpInActivation();

    public ValidMnpInBuilder voiceEngagementNumber(Integer i){
        this.voiceEngagementNumber = new VoiceEngagementNumber(i);
        if (mnpInActivation.isExist()) {
            mnpInActivation = new ValidMnpInActivation(
                    voiceEngagementNumber,
                    ((ValidMnpInActivation) mnpInActivation).getMnpInActivationDueDate()

            );
        }

        return this;
    }

    public ValidMnpInBuilder voiceMsisdn(String str){
        this.voiceMsisdn =  new VoiceMsisdn(str);
        return this;
    }

    public ValidMnpInBuilder mnpReservationNumber(String str){
        this.mnpReservationNumber =  new MnpReservationNumber(str);
        return this;
    }

    public ValidMnpInBuilder activationDueDate(String str){
        this.mnpInActivation = new ValidMnpInActivation(
                voiceEngagementNumber,
                new ValidMnpInActivationDueDate(str));
        return this;
    }

    public ValidMnpIn build(){
        MnpInPersonalInfo mnpInPersonalInfo;
        if (mnpPersonalInfo.isExist()) {
            mnpInPersonalInfo = new ValidMnpInPersonalInfo(this.voiceEngagementNumber, (ValidMnpPersonalInfo) mnpPersonalInfo);
        } else {
            mnpInPersonalInfo = new NotExistMnpInPersonalInfo();
        }
        return new ValidMnpIn(
                voiceEngagementNumber,
                voiceMsisdn,
                mnpReservationNumber,
                mnpInPersonalInfo,
                mnpInActivation
        );
    }


    public ValidMnpIn buildKoike(){
        mnpPersonalInfo = new ValidMnpPersonalInfo(
                new MnpFullName("小池　直樹"),
                new MnpFullNameKana("ｺｲｹ ﾅｵｷ"),
                MnpGender.MALE,
                new MnpBirthday("19840309"));
        return build();
    }

    public ValidMnpIn buildYamada(){
        voiceEngagementNumber = new VoiceEngagementNumber(2);
        mnpPersonalInfo = new ValidMnpPersonalInfo(
                new MnpFullName("山田　花子"),
                new MnpFullNameKana("ﾔﾏﾀﾞ ﾊﾅｺ"),
                MnpGender.FEMALE,
                new MnpBirthday("19770528"));
        return build();
    }

    public ValidMnpIn buildKoikeNull(){
        mnpPersonalInfo = new NotExistMnpPersonalInfo();
        return new ValidMnpIn(
                voiceEngagementNumber,
                voiceMsisdn,
                mnpReservationNumber,
                new NotExistMnpInPersonalInfo(),
                mnpInActivation
        );
    }



    public ValidMnpIn buildYamadaNull(){
        mnpPersonalInfo = new NotExistMnpPersonalInfo();
        return new ValidMnpIn(
                voiceEngagementNumber = new VoiceEngagementNumber(2),
                voiceMsisdn = new VoiceMsisdn("090-1234-5679"),
                mnpReservationNumber = new MnpReservationNumber("00-111-12346"),
                new NotExistMnpInPersonalInfo(),
                mnpInActivation
        );
    }


}
