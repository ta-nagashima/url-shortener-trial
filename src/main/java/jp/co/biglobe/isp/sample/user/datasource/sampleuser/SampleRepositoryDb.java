package jp.co.biglobe.isp.sample.user.datasource.sampleuser;

import jp.co.biglobe.isp.sample.user.datasource.sampleuser.db.SampleQueryMapper;
import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleRepository;
import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUser;
import jp.co.biglobe.isp.sample.user.domain.sampleuser.SampleUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepositoryDb implements SampleRepository {

    @Autowired
    private SampleQueryMapper sampleQueryMapper;

    @Override
    public SampleUser findBySampleUserId(SampleUserId sampleUserId) {

        SampleUser sampleUser = sampleQueryMapper.findSampleUserId(sampleUserId);

//        if (sampleUserId == null) {
//            return new NotExistMnpIn();
//        }

        return sampleUser;
    }

    @Override
    public void change(SampleUser sampleUser) {
        sampleQueryMapper.update(sampleUser);
    }

    @Override
    public SampleUser checkBOMyBatisExceptionTranslator(SampleUserId sampleUserId) {
        return sampleQueryMapper.invalid(sampleUserId);
    }
}
