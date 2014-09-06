package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFile;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class IdentificationFilesFactoryWeb implements IdentificationFilesFactory {
    @Override
    public IdentificationFiles create(IdentificationReceiptNumber identificationReceiptNumber,
                                      Date requestDate,
                                      IdentificationFile multipartFile1,
                                      IdentificationFile multipartFile2,
                                      IdentificationFile multipartFile3) {
        return new IdentificationFiles(
                identificationReceiptNumber,
                requestDate,
                multipartFile1,
                multipartFile2,
                multipartFile3
        );
    }
}
