package jp.co.biglobe.isp.sample.datasource.sampleuser.db;

import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUserId;
import org.apache.ibatis.annotations.Param;

public interface SampleQueryMapper {


    public SampleUser findSampleUserId(
            @Param("sampleUserId") SampleUserId sampleUserId
    );

}
