package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db.VoiceEngagementMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.*;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceEngagementRepositoryDb extends BaseRepositoryDb<VoiceEngagement, ValidVoiceEngagement> implements VoiceEngagementRepository {

    @Autowired
    private VoiceEngagementCancelRepositoryDb voiceEngagementCancelRepositoryDb;

    @Autowired
    private VoiceEngagementDisengageRepositoryDb voiceEngagementDisengageRepositoryDb;

    /**
     * 子エンティティを追加する
     */
    @Override
    protected void childInsert(ValidVoiceEngagement after) {
        voiceEngagementCancelRepositoryDb.insert(after.getVoiceEngagementCancel());
        voiceEngagementDisengageRepositoryDb.insert(after.getVoiceEngagementDisengage());
    }

    /**
     * 子エンティティを削除する
     */
    @Override
    protected void childDelete(ValidVoiceEngagement before) {
        voiceEngagementDisengageRepositoryDb.delete(before.getVoiceEngagementDisengage());
        voiceEngagementCancelRepositoryDb.delete(before.getVoiceEngagementCancel());
    }

    /**
     * 子エンティティを削除する（削除された場合は true を返す）
     */
    @Override
    protected boolean childDelete(ValidVoiceEngagement before, ValidVoiceEngagement after) {
        boolean deleted = voiceEngagementDisengageRepositoryDb.delete(before.getVoiceEngagementDisengage(), after.getVoiceEngagementDisengage());
        if (voiceEngagementCancelRepositoryDb.delete(before.getVoiceEngagementCancel(), after.getVoiceEngagementCancel())) {
            deleted = true;
        }
        return deleted;
    }

    /**
     * 子エンティティを追加・更新する
     */
    @Override
    protected void childInsertOrUpdate(ValidVoiceEngagement before, ValidVoiceEngagement after) {
        voiceEngagementCancelRepositoryDb.insertOrUpdate(before.getVoiceEngagementCancel(), after.getVoiceEngagementCancel());
        voiceEngagementDisengageRepositoryDb.insertOrUpdate(before.getVoiceEngagementDisengage(), after.getVoiceEngagementDisengage());
    }

    @Autowired
    private VoiceEngagementMapper queryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Override
    public VoiceEngagement findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber) {

        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);

        if (validVoiceEngagement == null) {
            return new NotExistVoiceEngagement();
        }

        return validVoiceEngagement;
    }

    @Override
    public ValidVoiceEngagement findByVoiceEngagementNumberForValid(VoiceEngagementNumber voiceEngagementNumber) {

        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);
        verifyValidVoiceEngagement(validVoiceEngagement);
        return validVoiceEngagement;
    }

    @Override
    public VoiceEngagement findByVoiceEngagementNumber(Identification identification) {

        if (identification.isNotExist()) {
            return new NotExistVoiceEngagement();
        }

        ValidIdentification validIdentification = (ValidIdentification) identification;

        return findByVoiceEngagementNumber(validIdentification.getVoiceEngagementNumber());

    }

    @Override
    public VoiceEngagement findByUserId(UserId userId) {

        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByUserId(userId);

        if (validVoiceEngagement == null) {
            return new NotExistVoiceEngagement();
        }

        return validVoiceEngagement;
    }

    @Override
    public VoiceEngagement findByEquipmentNumber(EquipmentNumber equipmentNumber) {
        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByEquipmentNumber(equipmentNumber);
        return verifyVoiceEngagement(validVoiceEngagement);
    }

    @Override
    public ValidVoiceEngagement findByEquipmentNumberForValid(EquipmentNumber equipmentNumber) {
        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByEquipmentNumber(equipmentNumber);
        verifyValidVoiceEngagement(validVoiceEngagement);
        return validVoiceEngagement;
    }

    @Override
    public ValidVoiceEngagement findByEquipmentNumberForUpdate(EquipmentNumber equipmentNumber) {
        // 音声通話オプション契約に対して、ロックを設定する
        VoiceEngagementNumber voiceEngagementNumber = queryMapper.lockByEquipmentNumber(equipmentNumber);
        if (voiceEngagementNumber == null) {
            throw new SystemCheckException("音声オプション契約が見つかりません", VoiceOptionAlarmIdentifier.DEFAULT);
        }
        // 音声通話オプション契約番号にて、データを取得する
        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByVoiceEngagementNumber(voiceEngagementNumber);
        return validVoiceEngagement;
    }

    @Override
    public VoiceEngagement findByLteThreeGEngagementNumberForEnable(LteThreeGEngagementNumber lteThreeGEngagementNumber) {
        java.util.Date date = requestEventTime.getDate();
        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByLteThreeGEngagementNumberUnderValid(
                lteThreeGEngagementNumber,
                new VoiceEngagementEndDateTime(date),
                VoiceEngagementStatus.ORDERED,
                VoiceEngagementStatus.ENGAGED,
                VoiceEngagementStatus.DISENGAGED);
        return verifyVoiceEngagement(validVoiceEngagement);
    }

    @Override
    public VoiceEngagement findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {
        ValidVoiceEngagement validVoiceEngagement = queryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);
        return verifyVoiceEngagement(validVoiceEngagement);
    }

    private VoiceEngagement verifyVoiceEngagement(VoiceEngagement voiceEngagement) {
        if (voiceEngagement == null) {
            return new NotExistVoiceEngagement();
        }
        return voiceEngagement;
    }

    private void verifyValidVoiceEngagement(ValidVoiceEngagement validVoiceEngagement) {
        if (validVoiceEngagement == null) {
            throw new SystemCheckException("音声オプション契約が見つかりません", VoiceOptionAlarmIdentifier.DEFAULT);
        }
    }
}
