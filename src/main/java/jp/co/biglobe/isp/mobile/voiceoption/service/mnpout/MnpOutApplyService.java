package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出申込（新規申込/再申込の両方)
 */
@Service
public class MnpOutApplyService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private MnpOutFactory mnpOutFactory;

    public void apply(EquipmentNumber equipmentNumber, MnpOutMsisdn mnpOutMsisdn){

        // 音声オプション契約番号取得
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForValid(equipmentNumber);

        // 申込可能なステータスかチェック
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(validVoiceEngagement);
        mnpOut.verifyApply();

        // 過去の転出情報があれば消す（なければなにもしない）
        mnpOutRepository.delete(mnpOut);

        // MNP転出情報を新規作成
        ValidMnpOut validMnpOut = mnpOutFactory.create(validVoiceEngagement, mnpOutMsisdn);

        // DBに反映して永続化
        //mnpOutRepository.register(mnpOut, validMnpOut);
        mnpOutRepository.insert(validMnpOut);

    }
}
