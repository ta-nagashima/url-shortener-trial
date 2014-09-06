package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.VoiceMnpOutId;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MnpOutQueryMapper extends CommonRegisterMapper<ValidMnpOut> {

    /**
     * 検索系
     */

    // MNP転出情報を音声通話オプション契約番号で検索する
    public ValidMnpOut findByVoiceEngagementNumber(@Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber);

    // MNP転出情報を音声通話オプション契約番号で検索する
    public VoiceEngagementNumber lockByVoiceEngagementNumber(@Param("voiceEngagementNumber") VoiceEngagementNumber voiceEngagementNumber);

}
