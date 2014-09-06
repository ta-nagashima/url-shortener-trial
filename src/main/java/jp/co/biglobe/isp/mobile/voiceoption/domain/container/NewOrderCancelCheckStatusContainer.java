package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndNewOrderCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.NewOrderCancelCheckStatus;
import lombok.Getter;

/**
* Created by masahirodoi on 2014/07/07.
*/
public class NewOrderCancelCheckStatusContainer {

    @Getter
    private final NewOrderCancelCheckStatus newOrderCancelCheckStatus;

    @Getter
    private final MnpOutAndNewOrderCancelCheckStatus mnpOutAndNewOrderCancelCheckStatus;

    public NewOrderCancelCheckStatusContainer(NewOrderCancelCheckStatus newOrderCancelCheckStatus, MnpOutAndNewOrderCancelCheckStatus mnpOutAndNewOrderCancelCheckStatus) {
        this.newOrderCancelCheckStatus = newOrderCancelCheckStatus;
        this.mnpOutAndNewOrderCancelCheckStatus = mnpOutAndNewOrderCancelCheckStatus;
    }
}
