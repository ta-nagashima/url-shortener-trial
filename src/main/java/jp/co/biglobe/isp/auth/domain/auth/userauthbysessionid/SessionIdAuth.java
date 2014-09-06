package jp.co.biglobe.isp.auth.domain.auth.userauthbysessionid;

import jp.co.biglobe.isp.auth.domain.user.*;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.ContractType;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.ContractTypeFactory;
import jp.co.biglobe.isp.mobile.biglobemember.domain.course.CourseId;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames=false)
@AllArgsConstructor
public class SessionIdAuth {

    private UserId userId;

    private FullName fullName;

    private CourseId courseId;


    public SessionIdAuthResult verify(){

        return new SessionIdAuthResult(userId, fullName, getContractType(courseId));
    }

    private ContractType getContractType(CourseId courseId){
        return new ContractTypeFactory().getContractType(courseId);
    }

    public ValidUserAuthTemp getSimpleBiglobeMember(){
        return new ValidUserAuthTemp(new UserId(userId.getValue()),new FullName(fullName.getValue()));
    }

}
