package jp.co.biglobe.isp.mobile.ltethreeg.domain.sim;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class ValidSimEntity {
    @Getter
    private final EquipmentNumber equipmentNumber;

    @Getter
    private final Msisdn msisdn;

    public ValidSimEntity(
            EquipmentNumber equipmentNumber,
            ValidMsisdn validMsisdn) {

        this.equipmentNumber = equipmentNumber;
        if(validMsisdn == null) {
            this.msisdn = new NotExistMsisdn();
        } else {
            this.msisdn = validMsisdn;
        }
    }

    public boolean isMsisdnConnectSet() {
        return msisdn.isExist();
    }
}
