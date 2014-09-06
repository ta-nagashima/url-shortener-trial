package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.ValidLteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db.IdentificationMapper;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.VoiceEngagementRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationRepositoryDb extends BaseRepositoryDb<Identification, ValidIdentification> implements IdentificationRepository {

    @Autowired
    private IdentificationUploadRepositoryDb identificationUploadRepositoryDb;

    @Autowired
    private IdentificationResultRepositoryDb identificationResultRepositoryDb;

    @Autowired
    private VoiceEngagementRepository voiceEngagementRepositoryDb;

    /**
     * 子エンティティを追加する
     */
    @Override
    protected void childInsert(ValidIdentification after) {
        identificationUploadRepositoryDb.insert(after.getIdentificationUpload());
        identificationResultRepositoryDb.insert(after.getIdentificationResult());
    }

    /**
     * 子エンティティを削除する
     */
    @Override
    protected void childDelete(ValidIdentification after) {
        identificationResultRepositoryDb.delete(after.getIdentificationResult());
        identificationUploadRepositoryDb.delete(after.getIdentificationUpload());
    }

    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    @Override
    protected boolean childDelete(ValidIdentification before, ValidIdentification after) {
        boolean deleted = identificationResultRepositoryDb.delete(before.getIdentificationResult(), after.getIdentificationResult());
        if (identificationUploadRepositoryDb.delete(before.getIdentificationUpload(), after.getIdentificationUpload())) {
            deleted = true;
        }
        return deleted;
    }

    /**
     * 子エンティティを追加・更新する
     */
    @Override
    protected void childInsertOrUpdate(ValidIdentification before, ValidIdentification after) {
        identificationUploadRepositoryDb.insertOrUpdate(before.getIdentificationUpload(), after.getIdentificationUpload());
        identificationResultRepositoryDb.insertOrUpdate(before.getIdentificationResult(), after.getIdentificationResult());
    }

    @Autowired
    private IdentificationMapper queryMapper;

    @Override
    public void delete(VoiceEngagementNumber voiceEngagementNumber) {

        ValidIdentification before = queryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (before != null) {
            delete(before);
        }

    }

    @Override
    public Identification findByIdentificationReceiptNumber(IdentificationReceiptNumber identificationReceiptNumber) {

        ValidIdentification validIdentification = queryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        if (validIdentification == null) {
            return new NotExistIdentification();
        }

        return validIdentification;
    }


    @Override
    public ValidIdentification findByIdentificationReceiptNumberForValid(IdentificationReceiptNumber identificationReceiptNumber) {

        ValidIdentification validIdentification = queryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        if (validIdentification == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validIdentification;
    }

    @Override
    public ValidIdentification findByIdentificationReceiptNumberForUpdate(IdentificationReceiptNumber identificationReceiptNumber) {

        IdentificationReceiptNumber identificationReceiptNumberLock = queryMapper.lockByIdentificationReceiptNumber(identificationReceiptNumber);

        if (identificationReceiptNumberLock == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidIdentification validIdentification = queryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        return validIdentification;
    }

    /**
     *
     * LteThreeGEngagementNumberを検索キーにするメソッドは
     * データから音声SIMへの交換が可能になった事により、
     * 同じLTE3G契約番号に紐づくレコードが複数出てきたので、
     * 一度、音声契約を問い合わせるようにした。
     *
     */



    @Override
    public Identification findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        VoiceEngagement voiceEngagement
                = voiceEngagementRepositoryDb.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        if (voiceEngagement.isNotExist()){
            return new NotExistIdentification();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement)voiceEngagement;

        ValidIdentification validIdentification = queryMapper.findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        if (validIdentification == null) {
            return new NotExistIdentification();
        }

        return validIdentification;
    }

    @Override
    public Identification findByLteThreeGEngagementNumber(VoiceEngagement voiceEngagement) {

        
        if (voiceEngagement.isNotExist()) {
            return new NotExistIdentification();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement;

        ValidIdentification validIdentification = queryMapper.findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        if (validIdentification == null) {
            return new NotExistIdentification();
        }

        return validIdentification;
    }

    @Override
    public Identification findByLteThreeGEngagementNumber(LteThreeGEngagementEntity lteThreeGEngagementEntity) {

        if (lteThreeGEngagementEntity.isInvalid()) {
            return new NotExistIdentification();
        }

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = (ValidLteThreeGEngagementEntity) lteThreeGEngagementEntity;

        VoiceEngagement voiceEngagement
                = voiceEngagementRepositoryDb.findByLteThreeGEngagementNumberForEnable(validLteThreeGEngagement.getLteThreeGEngagementNumber());

        if (voiceEngagement.isNotExist()){
            return new NotExistIdentification();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement)voiceEngagement;

        ValidIdentification validIdentification = queryMapper.findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        if (validIdentification == null) {
            return new NotExistIdentification();
        }

        return validIdentification;


    }

    @Override
    public ValidIdentification findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {


        VoiceEngagement voiceEngagement
                = voiceEngagementRepositoryDb.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        if (voiceEngagement.isNotExist()){
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement)voiceEngagement;


        IdentificationReceiptNumber identificationReceiptNumber = queryMapper.lockByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());

        if(identificationReceiptNumber == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidIdentification validIdentification = queryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        if (validIdentification == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validIdentification;
    }

    @Override
    public ValidIdentification findByLteThreeGEngagementNumberForUpdate(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        VoiceEngagement voiceEngagement
                = voiceEngagementRepositoryDb.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber);

        if (voiceEngagement.isNotExist()){
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement)voiceEngagement;


        IdentificationReceiptNumber identificationReceiptNumber = queryMapper.lockByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());



        if(identificationReceiptNumber == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。本人確認データの中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        ValidIdentification validIdentification = queryMapper.findByIdentificationReceiptNumber(identificationReceiptNumber);

        return validIdentification;
    }
}
