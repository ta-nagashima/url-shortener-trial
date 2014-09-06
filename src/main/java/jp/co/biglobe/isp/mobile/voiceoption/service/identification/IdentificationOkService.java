package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.IdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 本人確認結果がOKでなおかつMNP転入がなしだったときの結果を反映するサービス
 */

@Service
public class IdentificationOkService {

    @Autowired
    private IdentificationRepository identificationRepository;

    protected void reflect(IdentificationReceiptNumber identificationReceiptNumber,
                        ValidIdentificationResult validIdentificationResult
    ) {

        // Identificationのステータスを変更
        changeValidIdentificationStatus(
                identificationReceiptNumber,
                validIdentificationResult);
    }

    protected ValidIdentification changeValidIdentificationStatus(
            IdentificationReceiptNumber identificationReceiptNumber,
            ValidIdentificationResult validIdentificationResult
    ) {
        // 本人確認のレコードを検索
        ValidIdentification validIdentification
                = identificationRepository.findByIdentificationReceiptNumberForUpdate(identificationReceiptNumber);

        // 本人確認結果を変更する
        ValidIdentification reflectedValidIdentification
                = validIdentification.reflectIdentificationOkResult(validIdentificationResult);


        // 結果をDBに登録する
        identificationRepository.update(reflectedValidIdentification);

        return reflectedValidIdentification;
    }

}
