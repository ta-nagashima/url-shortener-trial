package jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.vpncorporation;

import jp.co.biglobe.isp.mobile.biglobemember.domain.ValidBiglobeMember;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.CorporationNumber;
import jp.co.biglobe.isp.mobile.biglobemember.domain.corporation.ValidCorporation;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement.VpnOptionCode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class VpnCorporationFactory {

    public static VpnCorporation create(VpnEngagementEntity vpnEngagementEntity, ValidBiglobeMember validBiglobeMember){

        if (vpnEngagementEntity.isVpnOrdered()) {
            ValidCorporation validCorporation = (ValidCorporation) validBiglobeMember.getCorporation();
            return new ValidVpnCorporation(validCorporation.getCorporationNumber());

        }

        return new NotExistVpnCorporation();
    }

}
