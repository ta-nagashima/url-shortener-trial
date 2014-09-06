package jp.co.biglobe.isp.mobile.callhistory.domain.summary.biglobeid;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.BiglobeIdSummary;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class BiglobeIdSummaryList {

    List<BiglobeIdSummary> list;

    public List<BiglobeIdSummary> getList(CallKind callKind) {
        if (list.stream().filter(s -> s.isCallKind(callKind)).noneMatch(s -> s.isExistChargeAmount())) return Collections.emptyList();
        return list.stream().filter(s -> s.isCallKind(callKind)).collect(Collectors.toList());

    }
}
