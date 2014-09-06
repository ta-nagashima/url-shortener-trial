package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 本人確認結果がNGの結果を反映するサービス
 */
@Service
public class IdentificationNgService {

    @Autowired
    private IdentificationRepository identificationRepository;


    protected void reflect(IdentificationReceiptNumber identificationReceiptNumber, ValidIdentificationResult validIdentificationResult
    ) {

        // 本人確認のレコードを検索
        ValidIdentification validIdentification
                = identificationRepository.findByIdentificationReceiptNumberForUpdate(identificationReceiptNumber);

        // 本人確認結果を変更する
        ValidIdentification reflectedValidIdentification
                = validIdentification.reflectIdentificationNgResult(validIdentificationResult);


        // 結果をDBに登録する
        identificationRepository.update(reflectedValidIdentification);

    }
}
