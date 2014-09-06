package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface MnpInRepository {

    public void insert(ValidMnpIn validMnpIn);

    public void update(ValidMnpIn validMnpIn);

    public void delete(VoiceEngagementNumber voiceEngagementNumber);

    public MnpIn findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber);

    public MnpIn findByVoiceEngagementNumber(VoiceEngagement voiceEngagement);

    public MnpIn findByIdentificationReceiptNumber(IdentificationReceiptNumber identificationReceiptNumber);

    public MnpIn findByVoiceMsisdnUnderValid(VoiceMsisdn voiceMsisdn);

    public MnpIn findByBiglobeDenwaMsisdnUnderValid(BiglobeDenwaMsisdn biglobeDenwaMsisdn);

    public ValidMnpIn findByVoiceEngagementNumberForValid(VoiceEngagementNumber voiceEngagementNumber);

    public ValidMnpIn findByVoiceEngagementNumberForUpdate(VoiceEngagementNumber voiceEngagementNumber);
}
