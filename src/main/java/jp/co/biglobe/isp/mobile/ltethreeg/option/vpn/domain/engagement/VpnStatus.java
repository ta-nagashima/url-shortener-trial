package jp.co.biglobe.isp.mobile.ltethreeg.option.vpn.domain.engagement;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VpnStatus {
    ORDERED,
    NOT_ORDERED;

    public boolean isOrdered(){
        return this.equals(VpnStatus.ORDERED);
    }


}
