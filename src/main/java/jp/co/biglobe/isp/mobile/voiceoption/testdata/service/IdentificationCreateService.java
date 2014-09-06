package jp.co.biglobe.isp.mobile.voiceoption.testdata.service;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.identificationresultid.IdentificationResultIdQueryMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.executiondate.ExecutionDate;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationStatusEvent;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.ValidIdentification;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ValidIdentificationResult;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.DocumentAcceptanceMeans;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationDocuments;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.identificationdocuments.IdentificationSubDocumentType;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.identificationresult.ngreasons.NotExistNgReasons;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.MnpInActivationDueDateFactory;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.mnpinactivationduedate.ValidMnpInActivationDueDate;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationOkService;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationOkWithMnpInService;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationResultReflectService;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpin.MnpInCheckService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationCreateService {

    @Autowired
    private MnpInCheckService mnpInCheckService;

    @Autowired
    private IdentificationOkService identificationOkService;

    @Autowired
    private IdentificationOkWithMnpInService identificationOkWithMnpInService;

    @Autowired
    private IdentificationResultReflectService identificationResultReflectService;

    @Autowired
    private IdentificationResultIdQueryMapper identificationResultIdQueryMapper;

    public void reflect(ValidIdentification validIdentification) {
        // 現在の日付の取得
        final String TODAY = new DateTime().toString("yyyyMMdd");

        IdentificationReceiptNumber identificationReceiptNumber = validIdentification.getIdentificationReceiptNumber();
        MnpIn mnpIn = mnpInCheckService.check(identificationReceiptNumber);

        IdentificationStatusEvent event = IdentificationStatusEvent.OK;

        identificationResultReflectService.reflect(
                event,
                identificationReceiptNumber,
                new ValidIdentificationResult(
                        identificationResultIdQueryMapper.create(),
                        identificationReceiptNumber,
                        new OperatorId("xyz12345"),
                        new ExecutionDate(TODAY),
                        new IdentificationDocuments(
                                DocumentAcceptanceMeans.FAX,
                                IdentificationDocumentType.OTHER,
                                IdentificationSubDocumentType.OTHER
                        ),
                        new NotExistNgReasons()
                ),
                MnpInActivationDueDateFactory.create(event, mnpIn, new ValidMnpInActivationDueDate(TODAY)),
                mnpIn
        );




        /*
        // 本人確認処理
        if(mnpIn.isExist()) {
            // MNP転入ありの場合の本人確認処理
            identificationOkWithMnpInService.reflect(
                    identificationReceiptNumber,
                    new ValidIdentificationResult(
                            new OperatorId("xyz12345"),
                            new ExecutionDate(TODAY),
                            new IdentificationDocuments(
                                    DocumentAcceptanceMeans.FAX,
                                    IdentificationDocumentType.LICENSE,
                                    IdentificationSubDocumentType.NOTHING
                            ),
                            new NotExistNgReasons()
                    ),
                    new ValidActivationDueDate(TODAY)
            );
        } else {
            // MNP転入なしの場合の本人確認処理
            identificationOkService.reflect(
                    identificationReceiptNumber,
                    new ValidIdentificationResult(
                            new OperatorId("xyz12345"),
                            new ExecutionDate(TODAY),
                            new IdentificationDocuments(
                                    DocumentAcceptanceMeans.FAX,
                                    IdentificationDocumentType.LICENSE,
                                    IdentificationSubDocumentType.NOTHING
                            ),
                            new NotExistNgReasons()
                    )
            );
        }
        */
    }


}
