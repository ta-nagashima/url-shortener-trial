package jp.co.biglobe.isp.sample.domain.sampleuser;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public final class SampleUser {
    @Getter
    private final SampleUserId sampleUserId;

    @Getter
    private final SampleUserName sampleUserName;
}
