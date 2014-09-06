package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db.MnpOutIdQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario.MnpOutPersonalInfoScenario;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.scenario.MnpOutPersonalInfoScenarioOutput;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnppersonalinfo.MnpGender;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutFactoryScenario implements MnpOutFactory {

    @Autowired
    private MnpOutIdQueryMapper mnpOutIdQueryMapper;

    @Autowired
    private MnpOutPersonalInfoScenario mnpOutPersonalInfoScenario;

    @Override
    public ValidMnpOut create(ValidVoiceEngagement validVoiceEngagement, MnpOutMsisdn mnpOutMsisdn){

        VoiceMnpOutId voiceMnpOutId = mnpOutIdQueryMapper.create();

        MnpOutPersonalInfoScenarioOutput mnpOutPersonalInfoScenarioOutput
                = mnpOutPersonalInfoScenario.findByBiglobeId(validVoiceEngagement.getVoiceEngagementLinkage().getUserId());


        ValidMnpOutPersonalInfo validMnpOutPersonalInfo = new ValidMnpOutPersonalInfo(
                voiceMnpOutId,
                mnpOutPersonalInfoScenarioOutput.getFullName(),
                mnpOutPersonalInfoScenarioOutput.getKana(),
                verifyGender(mnpOutPersonalInfoScenarioOutput.getSeibetu()),
                mnpOutPersonalInfoScenarioOutput.getBirthday()
        );

        return new ValidMnpOut(voiceMnpOutId,validVoiceEngagement.getVoiceEngagementNumber(), mnpOutMsisdn, validMnpOutPersonalInfo);
    }

    @Override
    public ValidMnpOut createByValidMnpIn(ValidVoiceEngagement validVoiceEngagement, ValidMnpIn validMnpIn){

        VoiceMnpOutId voiceMnpOutId = mnpOutIdQueryMapper.create();

        MnpOutPersonalInfoScenarioOutput mnpOutPersonalInfoScenarioOutput
                = mnpOutPersonalInfoScenario.findByBiglobeId(validVoiceEngagement.getVoiceEngagementLinkage().getUserId());


        ValidMnpOutPersonalInfo validMnpOutPersonalInfo = new ValidMnpOutPersonalInfo(
                voiceMnpOutId,
                mnpOutPersonalInfoScenarioOutput.getFullName(),
                mnpOutPersonalInfoScenarioOutput.getKana(),
                verifyGender(mnpOutPersonalInfoScenarioOutput.getSeibetu()),
                mnpOutPersonalInfoScenarioOutput.getBirthday()
        );

        MnpOutMsisdn mnpOutMsisdn = validMnpIn.getMnpOutMsisdn();

        return new ValidMnpOut(voiceMnpOutId, validVoiceEngagement.getVoiceEngagementNumber(), mnpOutMsisdn, validMnpOutPersonalInfo);
    }

    private MnpGender verifyGender(Integer seibetu){
        if(seibetu==1){
            return MnpGender.MALE;
        }

        if(seibetu==2){
            return MnpGender.FEMALE;
        }

        return MnpGender.UNKNOWN;
    }


}
