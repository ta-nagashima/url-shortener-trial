package jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BiglobeMemberInput {
    // 被操作者ID
    @Mapping("hi_sosaid[1]")
    private final UserId userId;

}
