package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
enum MnpOutEvent {

    MNP_OUT_REQUEST(
            Arrays.asList(MnpOutStatus.NO_REQUEST, MnpOutStatus.MNP_OUT_CANCEL),
            MnpOutStatus.REQUEST_WAITING,
            "転出依頼受付"
    ),
    MNP_RESERVATION_NUMBER_REQUEST(
            Arrays.asList(MnpOutStatus.REQUEST_WAITING),
            MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING,
            "事務局へMNP予約番号発行依頼"
    ),
    MNP_RESERVATION_NUMBER_ISSUE(
            Arrays.asList(MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING),
            MnpOutStatus.MNP_OUT_WAITING,
            "MNP予約番号発行完了"
    ),
    MNP_OUT_COMPLETE(
            Arrays.asList(MnpOutStatus.MNP_OUT_WAITING),
            MnpOutStatus.MNP_OUT_COMPLETION,
            "転出完了"
    ),
    MNP_OUT_CANCEL(
            Arrays.asList(MnpOutStatus.REQUEST_WAITING, MnpOutStatus.MNP_RESERVATION_NUMBER_WAITING, MnpOutStatus.MNP_OUT_WAITING),
            MnpOutStatus.MNP_OUT_CANCEL,
            "転出キャンセル"
    );

    private final List<MnpOutStatus> fromStatuses;
    @Getter
    private final MnpOutStatus toStatus;

    private final String message;

    boolean isNgFromStatus(MnpOutStatus mnpOutStatus){
        return !fromStatuses.contains(mnpOutStatus);
    }

}
