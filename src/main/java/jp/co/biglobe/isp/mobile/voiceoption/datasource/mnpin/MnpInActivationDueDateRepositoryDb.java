package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.*;
import org.springframework.stereotype.Repository;

@Repository
public class MnpInActivationDueDateRepositoryDb extends BaseRepositoryDb<MnpInActivation, ValidMnpInActivation> {
    @Override
    protected void childInsert(ValidMnpInActivation after) {

    }

    @Override
    protected void childDelete(ValidMnpInActivation before) {

    }

    @Override
    protected boolean childDelete(ValidMnpInActivation before, ValidMnpInActivation after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidMnpInActivation before, ValidMnpInActivation after) {

    }
}
