package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation;

import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumberForScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistVpnCorporation implements VpnCorporation{

    @Override
    public boolean isExist(){
        return false;
    }
}
