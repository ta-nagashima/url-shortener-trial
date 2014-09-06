package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.biglobedenwabirthdaycontainer.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.DefaultOperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BiglobeDenwaPersonalInfoScenarioInput {

    // 操作者ID（なんでもいい）
    @Mapping("sosaid")
    private final DefaultOperatorId operatorId = new DefaultOperatorId();

    // 被操作者ID
    @Mapping("hi_sosaid")
    private final UserId userId;

}
