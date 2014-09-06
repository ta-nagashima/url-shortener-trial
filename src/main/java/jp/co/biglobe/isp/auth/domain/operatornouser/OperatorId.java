package jp.co.biglobe.isp.auth.domain.operatornouser;

import jp.co.biglobe.isp.auth.domain.user.BiglobeId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 画面が担当者認証で使用するBiglobeId
 */

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class OperatorId implements BiglobeId {

    @Getter
    @NotNull
    private String value;

}
