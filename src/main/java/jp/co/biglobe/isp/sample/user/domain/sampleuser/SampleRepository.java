package jp.co.biglobe.isp.sample.user.domain.sampleuser;

public interface SampleRepository {
    public SampleUser findBySampleUserId(SampleUserId sampleUserId);

    public void change(SampleUser sampleUser);
}
