package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpreservationnumberregister;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.voiceoption.api.form.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

public class MnpReservationNumberRegisterRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceEngagementNumberForm voiceEngagementNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private MnpReservationNumberForm mnpReservationNumberForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private ExpireDateForm expireDateForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private VoiceOperatorIdForm operatorIdForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private ExecutionDateForm executionDateForm;
}
