package jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout;

import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;

/**
 * 音声通話オプション契約・ＭＮＰ転出情報
 */
public interface MnpOutRepository {

    // MNP転出情報を音声通話オプション契約番号で検索する
    public MnpOut findByVoiceEngagementNumber(VoiceEngagementNumber voiceEngagementNumber);

    // MNP転出情報を音声通話オプション契約番号で検索する
    public ValidMnpOut findByVoiceEngagementNumberForUpdate(VoiceEngagementNumber voiceEngagementNumber);

    // MNP転出情報を音声通話オプション契約番号で検索する
    public MnpOut findByVoiceEngagementNumber(VoiceEngagement voiceEngagement);

    public void insert(ValidMnpOut validMnpOut);

    public void delete(MnpOut mnpOut);

    // 新規MNP転出情報を更新する。
    public void update(ValidMnpOut validMnpOut);




}
