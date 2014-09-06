package jp.co.biglobe.isp.mobile.voiceoption.domain.charge.engagementmonthdisengagementcharge;

public interface EngagementMonthDisengagementCharge {

    public boolean isExist();

    public boolean isNotExist();

    public String getApiValueForExecuteDateTime();

    public String getApiValueForChargeAmount();

}
