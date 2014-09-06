package jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail;


import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.ValidMnpInActivationDueDate;

public interface VoiceSendMailRepository {

    public void mnpReservationNumberVoiceSendMail(UserId userId, FullName fullName, MnpReservationNumber mnpReservationNumber);

    public VoiceSendMailStatus sendIdentificationResultMail(UserId userId);

    public VoiceSendMailStatus sendIdentificationResultMail(UserId userId, ValidMnpInActivationDueDate mnpInActivationDueDate);

    public VoiceSendMailStatus sendMnpOutRequestMail(UserId userId);

    public VoiceSendMailStatus sendMnpOutReservationNumberMail(UserId userId, MnpReservationNumber mnpReservationNumber);
}
