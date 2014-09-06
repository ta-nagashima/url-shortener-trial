package jp.co.biglobe.isp.mobile.voiceoption.domain.container;

import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.DisengagementCheckStatus;
import jp.co.biglobe.isp.mobile.voiceoption.domain.policy.MnpOutAndDisengagementCheckStatus;
import lombok.Getter;

/**
* Created by masahirodoi on 2014/07/07.
*/
public class DisengagementCheckStatusContainer {

    @Getter
    private final DisengagementCheckStatus disengagementCheckStatus;

    @Getter
    private final MnpOutAndDisengagementCheckStatus mnpOutAndDisengagementCheckStatus;

    public DisengagementCheckStatusContainer(DisengagementCheckStatus disengagementCheckStatus, MnpOutAndDisengagementCheckStatus mnpOutAndDisengagementCheckStatus) {
        this.disengagementCheckStatus = disengagementCheckStatus;
        this.mnpOutAndDisengagementCheckStatus = mnpOutAndDisengagementCheckStatus;
    }
}
