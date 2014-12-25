package jp.co.biglobe.isp.sample.user.domain.sample;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class SampleDateTime {
    @Getter
    private final LocalDateTime value;
}
