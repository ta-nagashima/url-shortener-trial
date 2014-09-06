package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
enum VolumeChargeEvent {

    PURCHASE(
            Arrays.asList(VolumeChargeCompletionStatus.INIT),
            VolumeChargeCompletionStatus.REMAINED
    ),
    COMPLETE(
            Arrays.asList(VolumeChargeCompletionStatus.REMAINED),
            VolumeChargeCompletionStatus.COMPLETION
    ),
    PLAN_CHANGE(
            Arrays.asList(VolumeChargeCompletionStatus.REMAINED),
            VolumeChargeCompletionStatus.COMPLETION
    );

    private final List<VolumeChargeCompletionStatus> fromStatuses;

    @Getter
    private final VolumeChargeCompletionStatus toStatus;

    boolean isNgFromStatus(VolumeChargeCompletionStatus volumeChargeCompletionStatus){
        return !fromStatuses.contains(volumeChargeCompletionStatus);
    }
}
