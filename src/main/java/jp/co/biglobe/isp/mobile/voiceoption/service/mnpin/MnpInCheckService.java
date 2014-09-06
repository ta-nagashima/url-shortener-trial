package jp.co.biglobe.isp.mobile.voiceoption.service.mnpin;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MNP転入かどうかを返却するサービス
 * 本人確認結果反映APIで、アクティベーション予定日のバリデーションなどで使用するために作成
 * v1.0.0ではありえないが、MNP転入はあるが本人確認のレコードがない場合はNullオブジェクトを返す。
 */

@Service
public class MnpInCheckService {

    @Autowired
    private MnpInRepository mnpInRepository;


    public MnpIn check(IdentificationReceiptNumber identificationReceiptNumber) {

        return mnpInRepository.findByIdentificationReceiptNumber(identificationReceiptNumber);


    }

}
