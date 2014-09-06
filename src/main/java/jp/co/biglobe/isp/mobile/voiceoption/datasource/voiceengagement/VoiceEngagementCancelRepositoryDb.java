package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.ValidVoiceEngagementCancel;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.cancel.VoiceEngagementCancel;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceEngagementCancelRepositoryDb extends BaseRepositoryDb<VoiceEngagementCancel, ValidVoiceEngagementCancel> {
    @Override
    protected void childInsert(ValidVoiceEngagementCancel after) {

    }

    @Override
    protected void childDelete(ValidVoiceEngagementCancel before) {

    }

    @Override
    protected boolean childDelete(ValidVoiceEngagementCancel before, ValidVoiceEngagementCancel after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidVoiceEngagementCancel before, ValidVoiceEngagementCancel after) {

    }
}
