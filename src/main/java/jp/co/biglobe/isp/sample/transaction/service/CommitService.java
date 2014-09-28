package jp.co.biglobe.isp.sample.transaction.service;


import jp.co.biglobe.isp.sample.user.domain.sampleuser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitService {
    @Autowired
    private SampleRepository sampleRepository;

    public void commit() {

        SampleUser sampleUser = new SampleUser(
                new SampleUserId(1),
                new SampleUserName("小池直子"),
                SampleGender.FEMALE
        );

        sampleRepository.change(sampleUser);
    }
}
