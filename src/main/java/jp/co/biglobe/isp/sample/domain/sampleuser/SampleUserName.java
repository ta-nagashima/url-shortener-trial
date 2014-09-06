package jp.co.biglobe.isp.sample.domain.sampleuser;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public final class SampleUserName {
    @Getter
    private final String value;
}
