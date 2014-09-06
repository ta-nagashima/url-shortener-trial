package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames=false)
@EqualsAndHashCode
@AllArgsConstructor
public class NotExistEngagementMonthDisengagementCharge implements EngagementMonthDisengagementCharge {

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isNotExist() {
        return !isExist();
    }

    @Override
    public String getApiValueForExecuteDateTime() {
        return "";
    }

    @Override
    public String getApiValueForChargeAmount() {
        return "";
    }
}
