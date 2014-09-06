package jp.co.biglobe.isp.mobile.voiceoption.service.mnpout;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.EquipmentNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.*;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.ValidVoiceEngagement;
import jp.co.biglobe.isp.mobile.voiceoption.domain.voiceengagement.VoiceEngagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転出申込（新規申込キャンセル時のみ)
 */
@Service
public class MnpOutOnNewOrderCancelApplyService {

    @Autowired
    VoiceEngagementRepository voiceEngagementRepository;

    @Autowired
    MnpInRepository mnpInRepository;

    @Autowired
    private MnpOutRepository mnpOutRepository;

    @Autowired
    private MnpOutFactory mnpOutFactory;

    public void apply(EquipmentNumber equipmentNumber){

        // 音声オプション契約番号取得
        ValidVoiceEngagement validVoiceEngagement = voiceEngagementRepository.findByEquipmentNumberForUpdate(equipmentNumber);

        // MNP転入情報を参照
        ValidMnpIn validMnpIn = mnpInRepository.findByVoiceEngagementNumberForValid(validVoiceEngagement.getVoiceEngagementNumber());

        // 申込可能なステータスかチェック
        MnpOut mnpOut = mnpOutRepository.findByVoiceEngagementNumber(validVoiceEngagement);
        mnpOut.verifyApply();

        // 古いMnpOutを削除する（ない場合はなにもしない）
        mnpOutRepository.delete(mnpOut);


        // MNP転出情報を新規作成
        ValidMnpOut validMnpOut = mnpOutFactory.createByValidMnpIn(validVoiceEngagement, validMnpIn);

        // DBに反映して永続化
        //mnpOutRepository.register(mnpOut, validMnpOut);
        mnpOutRepository.insert(validMnpOut);

    }
}
