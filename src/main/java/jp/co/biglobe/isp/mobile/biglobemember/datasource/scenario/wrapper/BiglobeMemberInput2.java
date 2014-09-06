package jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.wrapper;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BiglobeMemberInput2 {

    // 呼び出しシナリオ名
    @Mapping("invoke_scenario_name")
    private final String invokeScenarioName = "M_MemberRefMeminfo2";

    // 被操作者ID
    @Mapping("hi_sosaid[1]")
    private final UserId userId;

}
