package jp.co.biglobe.isp.auth.datasource.user.scenario.sessionid;

import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseId;
import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class SessionIdAuthOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("hi_sosaid_out")
    @Getter
    private UserId userId;

    @Mapping("simei_kanji")
    @Getter
    private FullName name;

    @Mapping("course")
    @Getter
    private CourseId courseId;

}