package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationResultRepositoryDb extends BaseRepositoryDb<IdentificationResult, ValidIdentificationResult> {

    @Autowired
    private IdentificationNgRepositoryDb identificationNgRepositoryDb;

    /**
     * 子エンティティを追加する
     */
    @Override
    protected void childInsert(ValidIdentificationResult after) {
        identificationNgRepositoryDb.insert(after.getNgReasons());
    }

    /**
     * 子エンティティを削除する
     */
    @Override
    protected void childDelete(ValidIdentificationResult after) {
        identificationNgRepositoryDb.delete(after.getNgReasons());
    }

    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    @Override
    protected boolean childDelete(ValidIdentificationResult before, ValidIdentificationResult after) {
        boolean deleted = identificationNgRepositoryDb.delete(before.getNgReasons(), after.getNgReasons());
        return deleted;
    }

    /**
     * 子エンティティを追加・更新する
     */
    @Override
    protected void childInsertOrUpdate(ValidIdentificationResult before, ValidIdentificationResult after) {
        identificationNgRepositoryDb.insertOrUpdate(before.getNgReasons(), after.getNgReasons());
    }
}
