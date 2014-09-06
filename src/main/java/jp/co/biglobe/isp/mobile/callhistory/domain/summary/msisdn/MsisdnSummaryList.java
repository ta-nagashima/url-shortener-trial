package jp.co.biglobe.isp.mobile.callhistory.domain.summary.msisdn;

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind;
import jp.co.biglobe.isp.mobile.callhistory.domain.summary.single.MsisdnSummary;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class MsisdnSummaryList {

    List<MsisdnSummary> list;

    public List<MsisdnSummary> getList(CallKind callKind) {
        if (list.stream().filter(s -> s.isCallKind(callKind)).noneMatch(s -> s.isExistCountTransOptional())) return Collections.emptyList();
        return list.stream().filter(s -> s.isCallKind(callKind)).collect(Collectors.toList());
    }


    public Optional<MsisdnSummary> getMsisdnSummary(
            CallKind callKind, ValidMsisdn msisdn) {

        return list.stream().filter(s -> s.isCallKind(callKind))
                .filter(s -> s.getValidMsisdn().equals(msisdn)).findFirst();

    }
}
