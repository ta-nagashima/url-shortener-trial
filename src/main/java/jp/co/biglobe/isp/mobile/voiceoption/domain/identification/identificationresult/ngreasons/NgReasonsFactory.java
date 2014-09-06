package jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResultId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;

public class NgReasonsFactory {

    public static NgReasons create(IdentificationStatusEvent identificationStatusEvent,
                                   IdentificationResultId identificationResultId,
                                   NgReason ngReason,
                                   NgReasonDetail ngReasonDetail) {

        if (identificationStatusEvent == IdentificationStatusEvent.OK) {
            return new NotExistNgReasons();
        }

        if (ngReason == null) {
            throw new SystemCheckException("NgReasonsの生成に失敗しました。NgReasonがNULLです", VoiceOptionAlarmIdentifier.DEFAULT);
        }

        return new ValidNgReasons(identificationResultId, ngReason, ngReasonDetail);
    }
}
