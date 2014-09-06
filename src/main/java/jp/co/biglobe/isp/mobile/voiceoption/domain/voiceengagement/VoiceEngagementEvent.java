package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
enum VoiceEngagementEvent {

    ORDER(
            Arrays.asList(VoiceEngagementStatus.INIT),
            VoiceEngagementStatus.ORDERED
    ),
    CANCEL(
            Arrays.asList(VoiceEngagementStatus.ORDERED),
            VoiceEngagementStatus.CANCELED
    ),
    ENGAGE(
            Arrays.asList(VoiceEngagementStatus.ORDERED),
            VoiceEngagementStatus.ENGAGED
    ),
    DISENGAGE(
            Arrays.asList(VoiceEngagementStatus.ENGAGED),
            VoiceEngagementStatus.DISENGAGED
    ),
    DISENGAGE_CANCEL(
            Arrays.asList(VoiceEngagementStatus.DISENGAGED),
            VoiceEngagementStatus.ENGAGED
    ),
    MNP_OUT(
            Arrays.asList(VoiceEngagementStatus.ORDERED, VoiceEngagementStatus.ENGAGED),
            // MNP転出によって変更は起きないが便宜上入れておく。使うな
            null
    );

    private final List<VoiceEngagementStatus> fromStatuses;

    @Getter
    private final VoiceEngagementStatus toStatus;

    boolean isNgFromStatus(VoiceEngagementStatus voiceEngagementStatus){
        return !fromStatuses.contains(voiceEngagementStatus);
    }
}
