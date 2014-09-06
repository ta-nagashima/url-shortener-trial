package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;

import java.util.Date;

public interface IdentificationFilesFactory {
    public IdentificationFiles create(IdentificationReceiptNumber identificationReceiptNumber,
                                      Date requestDate,
                                      IdentificationFile multipartFile1,
                                      IdentificationFile multipartFile2,
                                      IdentificationFile multipartFile3);
}
