package jp.co.biglobe.isp.mobile.biglobemember.domain.course;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class CourseId {

    @Getter
    private final String value;

}
