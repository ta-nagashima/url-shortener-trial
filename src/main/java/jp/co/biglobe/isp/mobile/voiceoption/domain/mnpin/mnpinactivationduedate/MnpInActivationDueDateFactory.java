package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;

public class MnpInActivationDueDateFactory {

    public static MnpInActivationDueDate create(IdentificationStatusEvent identificationStatusEvent,
                                           MnpInActivationDueDate activationDueDate){

        if (identificationStatusEvent == IdentificationStatusEvent.NG) {
            return new NotExistMnpInActivationDueDate();
        }
        return activationDueDate;

    }

    public static MnpInActivationDueDate create(IdentificationStatusEvent identificationStatusEvent,
                                           MnpIn mnpIn,
                                           MnpInActivationDueDate activationDueDate){

        if (identificationStatusEvent == IdentificationStatusEvent.NG) {
            return new NotExistMnpInActivationDueDate();
        }

        if (mnpIn.isNotExist()) {
            return new NotExistMnpInActivationDueDate();
        }

        return activationDueDate;
    }

    public static ValidMnpInActivationDueDate createForValid(IdentificationStatusEvent identificationStatusEvent,
                                                             MnpIn mnpIn,
                                                             MnpInActivationDueDate mnpInActivationDueDate){

        if (identificationStatusEvent == IdentificationStatusEvent.NG) {
            throw new SystemCheckException("IdentificationStatusEventが不正です。NGの時はValidActivationDueDateを生成できません",
                    VoiceOptionAlarmIdentifier.DEFAULT);
        }

        if (mnpIn.isNotExist()) {
            throw new SystemCheckException("MnpInが不正です。MNP転入ではないときはValidActivationDueDateを生成できません",
                    VoiceOptionAlarmIdentifier.DEFAULT);
        }

        try {
            return (ValidMnpInActivationDueDate) mnpInActivationDueDate;
        } catch (ClassCastException ex) {
            throw new SystemCheckException("ActivationDueDateの型が不正です。ValidActivationDueDateに変換できません。",
                    VoiceOptionAlarmIdentifier.DEFAULT);
        }

    }
}
