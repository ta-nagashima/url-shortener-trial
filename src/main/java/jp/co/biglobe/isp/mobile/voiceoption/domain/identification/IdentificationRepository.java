package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface IdentificationRepository {

    public void insert(ValidIdentification validIdentification);

    public void update(ValidIdentification validIdentification);

    public void delete(VoiceEngagementNumber voiceEngagementNumber);

    public Identification findByIdentificationReceiptNumber(IdentificationReceiptNumber identificationReceiptNumber);

    public ValidIdentification findByIdentificationReceiptNumberForValid(IdentificationReceiptNumber identificationReceiptNumber);

    public ValidIdentification findByIdentificationReceiptNumberForUpdate(IdentificationReceiptNumber identificationReceiptNumber);

    public Identification findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public Identification findByLteThreeGEngagementNumber(VoiceEngagement voiceEngagement);

    public Identification findByLteThreeGEngagementNumber(LteThreeGEngagementEntity lteThreeGEngagementEntity);

    public ValidIdentification findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public ValidIdentification findByLteThreeGEngagementNumberForUpdate(LteThreeGEngagementNumber lteThreeGEngagementNumber);

}
