package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation;

import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumber;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumberForScenario;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidVpnCorporation implements VpnCorporation{

    @Getter
    private final VpnOptionCode vpnOptionCode = new VpnOptionCode();

    @Getter
    private final CorporationNumber corporationNumber;

    @Override
    public boolean isExist(){
        return true;
    }
}
