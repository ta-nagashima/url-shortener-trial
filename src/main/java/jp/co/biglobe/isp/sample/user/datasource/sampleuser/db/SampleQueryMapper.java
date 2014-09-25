package jp.co.biglobe.isp.sample.user.datasource.sampleuser.db;

import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUserId;
import org.apache.ibatis.annotations.Param;

public interface SampleQueryMapper {


    public SampleUser findSampleUserId(
            @Param("sampleUserId") SampleUserId sampleUserId
    );

    public void update(
            @Param("sampleUser") SampleUser sampleUser
    );

    public SampleUser invalid(
            @Param("sampleUserId") SampleUserId sampleUserId
    );

}
