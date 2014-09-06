package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;

public interface MnpInPersonalInfo extends CommonEntity {

    public String getMnpFullNameForApiValue();

    public String getMnpFullNameKanaForApiValue();

    public String getMnpGenderForApiValue();

    public String getMnpBirthdayForApiValue();
}
