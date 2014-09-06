package jp.co.biglobe.isp.mobile.biglobemember.domain.course;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class Course {

    private final CourseId beforeCourseId;

    private final CourseId afterCourseId;

    private final CourseSwitchingDate courseSwitchingDate;

    public boolean isCorporation(){
        return getContractType().isCorporation();
    }

    private CourseId getCurrentCourseId(){

        if(courseSwitchingDate.isFuture()){
            return beforeCourseId;
        }

        return afterCourseId;
    }

    private ContractType getContractType(){
        ContractTypeFactory contractTypeFactory = new ContractTypeFactory();
        return contractTypeFactory.getContractType(getCurrentCourseId());
    }

}
