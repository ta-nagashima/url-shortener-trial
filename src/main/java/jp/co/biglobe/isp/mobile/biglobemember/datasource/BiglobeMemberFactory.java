package jp.co.biglobe.isp.mobile.biglobemember.datasource;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobemember.datasource.scenario.BiglobeMemberOutput;
import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.biglobemember.domain.address.BiglobeMemberAddress;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.Corporation;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumber;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.NotExistCorporation;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.ValidCorporation;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.Course;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseId;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseSwitchingDate;
import org.springframework.stereotype.Component;

@Component
class BiglobeMemberFactory {


    ValidBiglobeMember create(UserId userId, BiglobeMemberOutput biglobeMemberOutput) {

        return new ValidBiglobeMember(
                userId,
                biglobeMemberOutput.getFullName(),
                createBiglobeMemberAddress(biglobeMemberOutput),
                createCourse(biglobeMemberOutput),
                createCorporation(biglobeMemberOutput)
        );
    }

    private BiglobeMemberAddress createBiglobeMemberAddress(BiglobeMemberOutput biglobeMemberOutput) {

        return new BiglobeMemberAddress(
                biglobeMemberOutput.getZipCode(),
                biglobeMemberOutput.getPrefectureCode(),
                biglobeMemberOutput.getCity(),
                biglobeMemberOutput.getHouseNumber(),
                biglobeMemberOutput.getBuilding()
        );
    }

    private Course createCourse(BiglobeMemberOutput biglobeMemberOutput){
        return new Course(
                biglobeMemberOutput.getBeforeCourseId(),
                biglobeMemberOutput.getAfterCourseId(),
                biglobeMemberOutput.getCourseSwitchingDate()
        );
    }

    private Corporation createCorporation(BiglobeMemberOutput biglobeMemberOutput) {

        Course course = createCourse(biglobeMemberOutput);

        if(course.isCorporation()){
            return new ValidCorporation(biglobeMemberOutput.getCorporationNumber());
        }

        return new NotExistCorporation();


    }

}
