package jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo;

public interface MnpPersonalInfo {

    public String getMnpFullNameForApiValue();

    public String getMnpFullNameKanaForApiValue();

    public String getMnpGenderForApiValue();

    public String getMnpBirthdayForApiValue();

    public boolean isExist();

    public boolean isNotExist();

}
