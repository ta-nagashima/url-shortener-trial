package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ExpireDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP予約番号登録
 */
@Service
public class MnpReservationNumberRegisterService {

    @Autowired
    MnpOutRepository mnpOutRepository;

    public void register(
            VoiceEngagementNumber voiceEngagementNumber,
            MnpReservationNumber mnpReservationNumber,
            ExpireDate expireDate,
            ExecutionDate executionDate,
            OperatorId operatorId
    ) {

        ValidMnpOut validMnpOut = mnpOutRepository.findByVoiceEngagementNumberForUpdate(voiceEngagementNumber);

        ValidMnpOut updatedValidMnpOut = validMnpOut.reservationNumberRegister(mnpReservationNumber,expireDate,
                executionDate,operatorId);

        mnpOutRepository.update(updatedValidMnpOut);

    }

}
