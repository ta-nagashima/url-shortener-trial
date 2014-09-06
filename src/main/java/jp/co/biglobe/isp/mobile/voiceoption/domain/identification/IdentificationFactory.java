package jp.co.biglobe.isp.mobile.voiceoption.domain.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

public interface IdentificationFactory {

    /**
     * 本人確認が必要な場合
     */
    public ValidIdentification create(
            IdentificationInitialRequestData identificationInitialRequestData,
            VoiceEngagementNumber voiceEngagementNumber);

    /**
     * 後から系で、必ず本受付状態の場合
     */
    public ValidIdentification createInitStatusForVoiceClerkRequestRequested(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber);

    /**
     * 既に店頭で本人確認を完了している場合など、本人確認が不要な場合
     */
    public ValidIdentification createOKForVoiceClerkRequestRequested(
            IdentificationInitialRequestDataForVoiceClerkRequestUnnecessary identificationInitialRequestDataForVoiceClerkRequestUnnecessary,
            VoiceEngagementNumber voiceEngagementNumber);

    public ValidIdentification createOKForVoiceClerkRequestRequested(
            IdentificationInitialRequestData identificationInitialRequestData,
            VoiceEngagementNumber voiceEngagementNumber);

}
