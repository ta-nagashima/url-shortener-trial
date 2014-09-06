package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * 仮受付復旧のためのイベント
 */

@AllArgsConstructor
enum VoiceClerkRequestStatusEvent {

    PROVISIONAL_RESUME(
            Arrays.asList(VoiceClerkRequestStatus.UNREQUESTED),
            VoiceClerkRequestStatus.REQUESTED,
            "仮受付復旧"
    );

    private final List<VoiceClerkRequestStatus> fromStatues;

    @Getter
    private final VoiceClerkRequestStatus toStatus;

    private final String message;

    boolean isNgFromStatus(VoiceClerkRequestStatus voiceClerkRequestStatus){
        return !fromStatues.contains(voiceClerkRequestStatus);
    }
}
