package jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario;

import jp.co.biglobe.isp.auth.domain.user.FullName;
import jp.co.biglobe.isp.mobile.biglobemember.domain.address.*;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumber;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseSwitchingDate;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

public class BiglobeMemberOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("simei_kanji[1]")
    @Getter
    private FullName fullName;

    @Mapping("zip[1]")
    @Getter
    private ZipCode zipCode;

    // 都道府県コード
    @Mapping("adr1c_zero_pad[1]")
    @Getter
    private PrefectureCode prefectureCode;

    @Mapping("adr1_without_districts[1]")
    @Getter
    private City city;

    @Mapping("adr2[1]")
    @Getter
    private HouseNumber houseNumber;

    @Mapping("adr3[1]")
    @Getter
    private Building building;

    @Mapping("kirikaemae_cource_c[1]")
    @Getter
    private CourseId beforeCourseId;

    @Mapping("kirikaego_cource_c[1]")
    @Getter
    private CourseId afterCourseId;

    @Mapping("cource_change_ymd[1]")
    @Getter
    private CourseSwitchingDate courseSwitchingDate;

    @Mapping("houjin_no[1]")
    @Getter
    private CorporationNumber corporationNumber;
}
