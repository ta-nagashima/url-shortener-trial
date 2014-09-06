package jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
enum CancelListOutPutStatusEvent {

    OUTPUT(
            Arrays.asList(CancelListOutPutStatus.NOT_OUTPUT),
            CancelListOutPutStatus.OUTPUT
    );

    private final List<CancelListOutPutStatus> fromStatuses;

    @Getter
    private final CancelListOutPutStatus toStatus;

    boolean isNgFromStatus(CancelListOutPutStatus cancelListOutPutStatus) {
        return !fromStatuses.contains(cancelListOutPutStatus);
    }
}
