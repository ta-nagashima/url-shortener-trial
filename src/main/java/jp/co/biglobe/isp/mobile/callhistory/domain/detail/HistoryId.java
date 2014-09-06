package jp.co.biglobe.isp.mobile.callhistory.domain.detail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 通話履歴を識別するキー項目
 *
 * 今回は使わないが、MybatisがVOがすべて同じ場合に一つにまとめてしまうという謎仕様に対応するために持たせている
 *
 */



@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class HistoryId {

    private final Integer value;

}
