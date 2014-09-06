package jp.co.biglobe.isp.sample.datasource.sampleuser;

import jp.co.biglobe.isp.sample.datasource.sampleuser.db.SampleQueryMapper;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.domain.sampleuser.SampleUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepositoryDb {

    @Autowired
    private SampleQueryMapper sampleQueryMapper;


    public SampleUser findBySampleUserId(SampleUserId sampleUserId) {

        SampleUser sampleUser = sampleQueryMapper.findSampleUserId(sampleUserId);

//        if (sampleUserId == null) {
//            return new NotExistMnpIn();
//        }

        return sampleUser;
    }

}
