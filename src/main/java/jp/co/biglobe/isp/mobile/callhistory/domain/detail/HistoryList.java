package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class HistoryList {

    @Getter
    List<History> list;


}
