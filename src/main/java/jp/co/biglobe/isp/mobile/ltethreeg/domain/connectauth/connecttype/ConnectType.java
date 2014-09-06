package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.connecttype;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/*
   概念上はエンティティであるべきだが、実装上、商品契約経由で帯域制御装置を操作しており、
 　エンティティとして性質を持つ必要性がないため、
 　Msisdnなどキー項目は持たせずエンティティとしても扱わない
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ConnectType {

    @Getter
    private final LteThreeGServicePlan lteThreeGServicePlan;

}
