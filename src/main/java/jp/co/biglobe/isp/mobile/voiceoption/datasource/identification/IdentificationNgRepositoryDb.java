package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NgReasons;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.ValidNgReasons;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationNgRepositoryDb extends BaseRepositoryDb<NgReasons, ValidNgReasons> {
    @Override
    protected void childInsert(ValidNgReasons after) {

    }

    @Override
    protected void childDelete(ValidNgReasons before) {

    }

    @Override
    protected boolean childDelete(ValidNgReasons before, ValidNgReasons after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidNgReasons before, ValidNgReasons after) {

    }
}
