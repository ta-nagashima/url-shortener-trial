package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
enum SpeedChargeEvent {

    PURCHASE(
            Arrays.asList(SpeedChargeCompletionStatus.INIT),
            SpeedChargeCompletionStatus.REMAINED
    ),
    COMPLETE(
            Arrays.asList(SpeedChargeCompletionStatus.REMAINED),
            SpeedChargeCompletionStatus.COMPLETION
    ),
    PLAN_CHANGE(
            Arrays.asList(SpeedChargeCompletionStatus.REMAINED),
            SpeedChargeCompletionStatus.COMPLETION
    );

    private final List<SpeedChargeCompletionStatus> fromStatuses;

    @Getter
    private final SpeedChargeCompletionStatus toStatus;

    boolean isNgFromStatus(SpeedChargeCompletionStatus speedChargeCompletionStatus){
        return !fromStatuses.contains(speedChargeCompletionStatus);
    }
}
