package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.extension.domain.CommonEntity;

public interface MnpOutPersonalInfo extends CommonEntity {

    public String getMnpFullNameForApiValue();

    public String getMnpFullNameKanaForApiValue();

    public String getMnpGenderForApiValue();

    public String getMnpBirthdayForApiValue();

    public boolean isNotExist();

}
