package jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistory;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LimitHistoryForView {

    private final Integer id;

    @Getter
    private final LimitHistory limitHistory;
}
