package jp.co.biglobe.isp.mobile.ltethreeg.domain.view.limithistorylistrefer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class LimitHistoryList {

    @Getter
    private final List<LimitHistoryForView> list;
}
