package jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


/**
 * 2014/8/8
 * もともとパッケージプライベートだったがBiglobeDenwaLinkageApplyFromAppEventのテストのためにpublicに変更
 */

@AllArgsConstructor
public enum BiglobeDenwaLinkageEvent {

    WAITING_REGISTER(
            "センターに登録依頼する",
            Arrays.asList(
                    BiglobeDenwaLinkageStatus.NO_LINKAGE,
                    BiglobeDenwaLinkageStatus.REGISTERED_IN_OTHER
            ),
            BiglobeDenwaLinkageStatus.WAITING_REGISTER
    ),
    WAITING_REGISTER_RESULT(
            "アライアンス先に登録連携依頼をする",
            Arrays.asList(BiglobeDenwaLinkageStatus.WAITING_REGISTER),
            BiglobeDenwaLinkageStatus.WAITING_REGISTER_RESULT
    ),
    REGISTERED(
            "登録結果OKを反映する",
            Arrays.asList(
                    BiglobeDenwaLinkageStatus.WAITING_REGISTER_RESULT,
                    BiglobeDenwaLinkageStatus.WAITING_REREGISTER_RESULT
            ),
            BiglobeDenwaLinkageStatus.REGISTERED
    ),
    UNABLE_TO_REGISTER(
            "登録結果NGを反映する",
            Arrays.asList(
                    BiglobeDenwaLinkageStatus.WAITING_REGISTER_RESULT,
                    BiglobeDenwaLinkageStatus.WAITING_REREGISTER_RESULT
            ),
            BiglobeDenwaLinkageStatus.UNABLE_TO_REGISTER
    ),
    REGISTERED_IN_OTHER(
            "他社登録済みを反映する",
            Arrays.asList(BiglobeDenwaLinkageStatus.UNABLE_TO_REGISTER),
            BiglobeDenwaLinkageStatus.REGISTERED_IN_OTHER
    ),
    WAITING_REREGISTER(
            "センターに再登録を依頼する",
            Arrays.asList(
                    BiglobeDenwaLinkageStatus.UNABLE_TO_REGISTER,
                    BiglobeDenwaLinkageStatus.WAITING_REGISTER_RESULT,
                    BiglobeDenwaLinkageStatus.WAITING_REREGISTER_RESULT
            ),
            BiglobeDenwaLinkageStatus.WAITING_REREGISTER
    ),
    WAITING_REREGISTER_RESULT(
            "アライアンス先に再登録連携依頼をする",
            Arrays.asList(BiglobeDenwaLinkageStatus.WAITING_REREGISTER),
            BiglobeDenwaLinkageStatus.WAITING_REREGISTER_RESULT
    ),
    WAITING_REMOVE(
            "センターに解除を依頼する",
            Arrays.asList(BiglobeDenwaLinkageStatus.REGISTERED),
            BiglobeDenwaLinkageStatus.WAITING_REMOVE
    ),
    WAITING_REMOVE_RESULT(
            "アライアンス先に解除を依頼する",
            Arrays.asList(BiglobeDenwaLinkageStatus.WAITING_REMOVE),
            BiglobeDenwaLinkageStatus.WAITING_REMOVE_RESULT
    ),
    REMOVE(
            "連携解除を反映する",
            Arrays.asList(
                    BiglobeDenwaLinkageStatus.WAITING_REMOVE_RESULT,
                    BiglobeDenwaLinkageStatus.UNABLE_TO_REGISTER,
                    BiglobeDenwaLinkageStatus.REGISTERED_IN_OTHER
            ),
            BiglobeDenwaLinkageStatus.NO_LINKAGE
    );

    private final String message;
    private final List<BiglobeDenwaLinkageStatus> fromStatuses;

    @Getter
    private final BiglobeDenwaLinkageStatus toStatus;

    boolean isNgFromStatus(BiglobeDenwaLinkageStatus biglobeDenwaLinkageStatus){
        return !fromStatuses.contains(biglobeDenwaLinkageStatus);
    }

}
