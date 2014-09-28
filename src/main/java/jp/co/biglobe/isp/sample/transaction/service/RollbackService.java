package jp.co.biglobe.isp.sample.transaction.service;


import jp.co.biglobe.isp.sample.user.domain.sampleuser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RollbackService {
    @Autowired
    private SampleRepository sampleRepository;

    public void rollback() {

        SampleUser sampleUser = new SampleUser(
                new SampleUserId(1),
                new SampleUserName("小池直子"),
                SampleGender.FEMALE
        );

        sampleRepository.change(sampleUser);

        throw new RuntimeException("擬似的に例外をスローして、ロールバックさせる");
    }
}
