package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.MnpOutQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.MnpOutRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.NotExistMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 音声通話オプション契約・ＭＮＰ転出情報
 */
@Repository
public class MnpOutRepositoryDb extends BaseRepositoryDb<MnpOut, ValidMnpOut> implements MnpOutRepository {

    @Autowired
    private MnpOutQueryMapper mnpOutQueryMapper;

    @Autowired
    private MnpOutPersonalInfoRepositoryDb mnpOutPersonalInfoRepositoryDb;

    @Autowired
    private MnpOutNumberRepositoryDb mnpOutNumberRepositoryDb;

    @Autowired
    private MnpOutCompleteRepositoryDb mnpOutCompleteRepositoryDb;

    /**
     * 子エンティティを追加する
     */
    @Override
    protected void childInsert(ValidMnpOut after) {
        mnpOutPersonalInfoRepositoryDb.insert(after.getValidMnpOutPersonalInfo());
        mnpOutNumberRepositoryDb.insert(after.getMnpOutReservationNumber());
        mnpOutCompleteRepositoryDb.insert(after.getMnpOutCompletion());
    }

    /**
     * 子エンティティを削除する
     */
    @Override
    protected void childDelete(ValidMnpOut before) {
        mnpOutPersonalInfoRepositoryDb.delete(before.getValidMnpOutPersonalInfo());
        mnpOutNumberRepositoryDb.delete(before.getMnpOutReservationNumber());
        mnpOutCompleteRepositoryDb.delete(before.getMnpOutCompletion());
    }


    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    @Override
    protected boolean childDelete(ValidMnpOut before, ValidMnpOut after) {
        boolean deletedPersonalInfo = mnpOutPersonalInfoRepositoryDb.delete(before.getValidMnpOutPersonalInfo(), after.getValidMnpOutPersonalInfo());
        boolean deletedNumber = mnpOutNumberRepositoryDb.delete(before.getMnpOutReservationNumber(), after.getMnpOutReservationNumber());
        boolean deletedComplete = mnpOutCompleteRepositoryDb.delete(before.getMnpOutCompletion(), after.getMnpOutCompletion());
        return (deletedPersonalInfo || deletedNumber || deletedComplete);
    }

    /**
     * 子エンティティを追加・更新する
     */
    @Override
    protected void childInsertOrUpdate(ValidMnpOut before, ValidMnpOut after) {
        mnpOutPersonalInfoRepositoryDb.insertOrUpdate(before.getValidMnpOutPersonalInfo(), after.getValidMnpOutPersonalInfo());
        mnpOutNumberRepositoryDb.insertOrUpdate(before.getMnpOutReservationNumber(), after.getMnpOutReservationNumber());
        mnpOutCompleteRepositoryDb.insertOrUpdate(before.getMnpOutCompletion(), after.getMnpOutCompletion());
    }


    // MNP転出情報を音声通話オプション契約番号で検索する
    @Override
    public MnpOut findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber) {

        ValidMnpOut validMnpOut = mnpOutQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (validMnpOut == null) {
            return new NotExistMnpOut();
        }

        return validMnpOut;
    }

    @Override
    public ValidMnpOut findByVoiceEngagementNumberForUpdate(VoiceEngagementNumber voiceEngagementNumber) {

        VoiceEngagementNumber lockVoiceEngagementNumber = mnpOutQueryMapper.lockByVoiceEngagementNumber(voiceEngagementNumber);

        if (lockVoiceEngagementNumber == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。MNP転出情報の中に該当データが存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return mnpOutQueryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

    }

    // MNP転出情報を音声通話オプション契約番号で検索する
    @Override
    public MnpOut findByVoiceEngagementNumber(VoiceEngagement voiceEngagement) {

        if (voiceEngagement.isNotExist()) {
            return new NotExistMnpOut();
        }

        ValidVoiceEngagement validVoiceEngagement = (ValidVoiceEngagement) voiceEngagement;
        return findByVoiceEngagementNumber(validVoiceEngagement.getVoiceEngagementNumber());
    }

}
