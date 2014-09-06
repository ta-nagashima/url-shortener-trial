package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpinputitems;

import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.*;

public class MnpInputItemsFactoryDb {

    public ValidMnpPersonalInfo create(MnpFullName mnpFullName,
                                     MnpFullNameKana mnpFullNameKana,
                                     MnpGender mnpGender,
                                     MnpBirthday mnpBirthday){

        return new ValidMnpPersonalInfo(mnpFullName,mnpFullNameKana,mnpGender,mnpBirthday);
    };

}
