package jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailOutput;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.voicesendmailinput.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.ValidMnpInActivationDueDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voicesendmail.VoiceSendMailStatus;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceSendMailRepositoryScenario implements VoiceSendMailRepository {

    @Autowired
    private VoiceSendMailScenario voiceSendMailScenario;

    @Autowired
    RequestEventTime requestEventTime;

    // MNP転出予約番号通知メール送信を行なうシナリオの呼び出し
    @Override
    public void mnpReservationNumberVoiceSendMail(UserId userId, FullName fullName, MnpReservationNumber mnpReservationNumber) {
        VoiceSendMailDate voiceSendMailDate = new VoiceSendMailDate(requestEventTime.getDate());

        MnpReservationNumberVoiceSendMailInput mnpReservationNumberVoiceSendMailInput = new MnpReservationNumberVoiceSendMailInput(
                userId,
                voiceSendMailDate.getDay(),
                fullName,
                mnpReservationNumber
        );
        voiceSendMailScenario.send(mnpReservationNumberVoiceSendMailInput);
    }


    // 本人確認結果を送信するシナリオを呼び出し
    @Override
    public VoiceSendMailStatus sendIdentificationResultMail(UserId userId) {

        // シナリオのインプットを作成
        VoiceSendMailInput voiceSendMailInput = new IdentificationResultVoiceSendMailInput(
                userId,
                new VoiceSendMailDate(requestEventTime.getDate()).getDay()
        );

        VoiceSendMailOutput voiceSendMailOutput = voiceSendMailScenario.sendNoException(voiceSendMailInput);

        return voiceSendMailOutput.getIdentificationResultMailStatus();

    }


    // 本人確認結果を送信するシナリオを呼び出し
    @Override
    public VoiceSendMailStatus sendIdentificationResultMail(UserId userId, ValidMnpInActivationDueDate validMnpInActivationDueDate) {

        // シナリオのインプットを作成
        VoiceSendMailInput voiceSendMailInput = new IdentificationResultWithMnpInVoiceSendMailInput(
                userId,
                new VoiceSendMailDate(requestEventTime.getDate()).getDay(),
                validMnpInActivationDueDate
        );

        VoiceSendMailOutput voiceSendMailOutput = voiceSendMailScenario.sendNoException(voiceSendMailInput);

        return voiceSendMailOutput.getIdentificationResultMailStatus();
    }

    // 転出申し込み受付のメールを送信するシナリオを呼び出し
    @Override
    public VoiceSendMailStatus sendMnpOutRequestMail(UserId userId) {

        // シナリオのインプットを作成
        MnpOutRequestSendMailInput mnpOutRequestSendMailInput = new MnpOutRequestSendMailInput(
                userId,
                new VoiceSendMailDate(requestEventTime.getDate()).getDay()
        );

        VoiceSendMailOutput voiceSendMailOutput = voiceSendMailScenario.sendNoException(mnpOutRequestSendMailInput);

        return voiceSendMailOutput.getIdentificationResultMailStatus();

    }

    // 転出申し込み受付のメールを送信するシナリオを呼び出し
    @Override
    public VoiceSendMailStatus sendMnpOutReservationNumberMail(UserId userId, MnpReservationNumber mnpReservationNumber) {

        // シナリオのインプットを作成
        MnpOutReservationNumberSendMailInput mnpOutReservationNumberSendMailInput = new MnpOutReservationNumberSendMailInput(
                userId,
                new VoiceSendMailDate(requestEventTime.getDate()).getDay(),
                mnpReservationNumber
        );

        VoiceSendMailOutput voiceSendMailOutput = voiceSendMailScenario.sendNoException(mnpOutReservationNumberSendMailInput);

        return voiceSendMailOutput.getIdentificationResultMailStatus();

    }


}