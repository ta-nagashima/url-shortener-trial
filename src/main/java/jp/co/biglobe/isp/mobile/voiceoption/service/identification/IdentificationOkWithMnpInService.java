package jp.co.biglobe.isp.mobile.voiceoption.service.identification;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpInRepository;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.ValidMnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.ValidMnpInActivationDueDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationOkWithMnpInService {

    @Autowired
    private IdentificationOkService identificationOkService;

    @Autowired
    private MnpInRepository mnpInRepository;


    protected void reflect(IdentificationReceiptNumber identificationReceiptNumber,
                           ValidIdentificationResult validIdentificationResult,
                           ValidMnpInActivationDueDate validMnpInActivationDueDate
    ) {

        // Identificationのステータスを変更
        ValidIdentification reflectedValidIdentification
                = identificationOkService.changeValidIdentificationStatus(identificationReceiptNumber, validIdentificationResult);

        // アクティベーション予定日を登録
        addMnpInActivationDate(validMnpInActivationDueDate, reflectedValidIdentification);


    }

    protected void addMnpInActivationDate(
            ValidMnpInActivationDueDate validMnpInActivationDueDate,
            ValidIdentification reflectedValidIdentification
    ) {

        // MnpInを検索する
        ValidMnpIn validMnpIn = mnpInRepository.findByVoiceEngagementNumberForUpdate(
                reflectedValidIdentification.getVoiceEngagementNumber());

        // アクティベーション予定日を設定
        ValidMnpIn editedMnpIn = validMnpIn.inputActivationDate(validMnpInActivationDueDate, reflectedValidIdentification);

        // 結果をDBに登録する
        mnpInRepository.update(editedMnpIn);
    }
}
