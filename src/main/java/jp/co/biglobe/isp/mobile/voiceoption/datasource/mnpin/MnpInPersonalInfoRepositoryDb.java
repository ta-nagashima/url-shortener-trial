package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpin.db.MnpInPersonalInfoQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.NotExistMnpInPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpInPersonalInfo;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MnpInPersonalInfoRepositoryDb extends BaseRepositoryDb<MnpInPersonalInfo, ValidMnpInPersonalInfo> {
    @Override
    protected void childInsert(ValidMnpInPersonalInfo after) {

    }

    @Override
    protected void childDelete(ValidMnpInPersonalInfo before) {

    }

    @Override
    protected boolean childDelete(ValidMnpInPersonalInfo before, ValidMnpInPersonalInfo after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidMnpInPersonalInfo before, ValidMnpInPersonalInfo after) {

    }
}
