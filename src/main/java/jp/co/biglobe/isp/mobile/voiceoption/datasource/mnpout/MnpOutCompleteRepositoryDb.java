package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.MnpOutCompletion;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutcompletion.ValidMnpOutCompletion;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutCompleteRepositoryDb extends BaseRepositoryDb<MnpOutCompletion, ValidMnpOutCompletion> {
    @Override
    protected void childInsert(ValidMnpOutCompletion after) {

    }

    @Override
    protected void childDelete(ValidMnpOutCompletion before) {

    }

    @Override
    protected boolean childDelete(ValidMnpOutCompletion before, ValidMnpOutCompletion after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidMnpOutCompletion before, ValidMnpOutCompletion after) {

    }
}
