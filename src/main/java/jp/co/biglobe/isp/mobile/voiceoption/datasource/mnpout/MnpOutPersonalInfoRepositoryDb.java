package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOutPersonalInfo;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutPersonalInfoRepositoryDb extends BaseRepositoryDb<MnpOutPersonalInfo, ValidMnpOutPersonalInfo> {

    @Override
    protected void childInsert(ValidMnpOutPersonalInfo after) {

    }

    @Override
    protected void childDelete(ValidMnpOutPersonalInfo before) {

    }

    @Override
    protected boolean childDelete(ValidMnpOutPersonalInfo before, ValidMnpOutPersonalInfo after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidMnpOutPersonalInfo before, ValidMnpOutPersonalInfo after) {

    }
}
