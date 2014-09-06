package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutApplyCancelCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutApplyCheckStatus;
import lombok.Getter;

/**
* Created by masahirodoi on 2014/07/07.
*/
public class MnpOutApplyCheckStatusContainer {

    @Getter
    private final MnpOutApplyCheckStatus mnpOutApplyCheckStatus;

    @Getter
    private final MnpOutApplyCancelCheckStatus mnpOutApplyCancelCheckStatus;

    public MnpOutApplyCheckStatusContainer(MnpOutApplyCheckStatus mnpOutApplyCheckStatus, MnpOutApplyCancelCheckStatus mnpOutApplyCancelCheckStatus) {
        this.mnpOutApplyCheckStatus = mnpOutApplyCheckStatus;
        this.mnpOutApplyCancelCheckStatus = mnpOutApplyCancelCheckStatus;
    }
}
