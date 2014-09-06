package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.ValidVoiceEngagementDisengage;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.disengage.VoiceEngagementDisengage;
import org.springframework.stereotype.Repository;

@Repository
public class VoiceEngagementDisengageRepositoryDb extends BaseRepositoryDb<VoiceEngagementDisengage, ValidVoiceEngagementDisengage> {
    @Override
    protected void childInsert(ValidVoiceEngagementDisengage after) {

    }

    @Override
    protected void childDelete(ValidVoiceEngagementDisengage before) {

    }

    @Override
    protected boolean childDelete(ValidVoiceEngagementDisengage before, ValidVoiceEngagementDisengage after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidVoiceEngagementDisengage before, ValidVoiceEngagementDisengage after) {

    }
}
