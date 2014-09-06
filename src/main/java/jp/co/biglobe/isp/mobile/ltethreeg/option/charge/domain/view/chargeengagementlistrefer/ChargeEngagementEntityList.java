package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.view.chargeengagementlistrefer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class ChargeEngagementEntityList {

    @Getter
    private final List<ChargeEngagementEntity> list;
}
