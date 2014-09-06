package jp.co.biglobe.isp.mobile.ltethreeg.datasource.ltethreegengagement;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.ltethreegengagement.db.LteThreeGEngagementQueryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.alarm.VoiceOptionAlarmIdentifier;
import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class LteThreeGEngagementRepositoryDb implements LteThreeGEngagementRepository {

    @Autowired
    private LteThreeGEngagementQueryMapper lteThreeGEngagementQueryMapper;

    @Override
    public LteThreeGEngagementEntity findByUserId(UserId userId){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findByUserId(userId, new Date());

        if(validLteThreeGEngagement == null) {
            return new NotExistLteThreeGEngagementEntity();
        }

        return validLteThreeGEngagement;
    }

    @Override
    public ValidLteThreeGEngagementEntity findByUserIdForValid(UserId userId){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findByUserId(userId, new Date());

        if(validLteThreeGEngagement == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。LTE・3G契約が存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validLteThreeGEngagement;
    }

    @Override
    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumberForValidForEnable(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findByLteThreeGEngagementNumberForEnable(lteThreeGEngagementNumber, new Date());

        if(validLteThreeGEngagement == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。LTE・3G契約が存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validLteThreeGEngagement;

    }

    @Override
    public ValidLteThreeGEngagementEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if(validLteThreeGEngagement == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。LTE・3G契約が存在しませんでした。", VoiceOptionAlarmIdentifier.DEFAULT
            );
        }

        return validLteThreeGEngagement;
    }

    @Override
    public LteThreeGEngagementEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if(validLteThreeGEngagement == null) {
            return new NotExistLteThreeGEngagementEntity();
        }

        return validLteThreeGEngagement;
    }

    @Override
    public LteThreeGEngagementEntity findVoiceSimEngagementByMsisdn(Msisdn msisdn){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement = lteThreeGEngagementQueryMapper.findVoiceSimEngagementByMsisdn(msisdn, new Date());

        if(validLteThreeGEngagement == null) {
            return new NotExistLteThreeGEngagementEntity();
        }

        return validLteThreeGEngagement;
    }

    @Override
    public LteThreeGEngagementEntity findVoiceSimEngagementByBiglobeDenwaMsisdn(BiglobeDenwaMsisdn biglobeDenwaMsisdn){

        ValidLteThreeGEngagementEntity validLteThreeGEngagement =
                lteThreeGEngagementQueryMapper.findVoiceSimEngagementByBiglobeDenwaMsisdn(biglobeDenwaMsisdn,new Date());

        if(validLteThreeGEngagement == null) {
            return new NotExistLteThreeGEngagementEntity();
        }

        return validLteThreeGEngagement;
    }


}
