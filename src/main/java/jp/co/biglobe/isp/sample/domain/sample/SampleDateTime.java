package jp.co.biglobe.isp.sample.domain.sample;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class SampleDateTime {
    @Getter
    private final Date value;
}
