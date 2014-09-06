package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.IdentificationUpload;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.upload.ValidIdentificationUpload;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationUploadRepositoryDb extends BaseRepositoryDb<IdentificationUpload, ValidIdentificationUpload> {
    @Override
    protected void childInsert(ValidIdentificationUpload after) {

    }

    @Override
    protected void childDelete(ValidIdentificationUpload before) {

    }

    @Override
    protected boolean childDelete(ValidIdentificationUpload before, ValidIdentificationUpload after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidIdentificationUpload before, ValidIdentificationUpload after) {

    }

}
