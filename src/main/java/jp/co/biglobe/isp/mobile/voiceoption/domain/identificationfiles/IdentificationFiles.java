package jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.IdentificationReceiptNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class IdentificationFiles {
    @Getter
    private final IdentificationReceiptNumber identificationReceiptNumber;
    private final Date requestTime;
    private final IdentificationFile identificationFile1;
    private final IdentificationFile identificationFile2;
    private final IdentificationFile identificationFile3;

    public IdentificationFiles(IdentificationReceiptNumber identificationReceiptNumber,
                               Date requestTime,
                               IdentificationFile identificationFile1,
                               IdentificationFile identificationFile2,
                               IdentificationFile identificationFile3) {
        this.identificationReceiptNumber = identificationReceiptNumber;
        this.requestTime = new Date(requestTime.getTime()); // ちゃんとコピーを作って保持する
        this.identificationFile1 = identificationFile1;
        this.identificationFile2 = identificationFile2;
        this.identificationFile3 = identificationFile3;
    }

    public Date getRequestTime() {
        return new Date(requestTime.getTime()); // ちゃんとコピーを作って返す
    }

    public ValidIdentificationFile[] getIdentificationFiles() {
        List<ValidIdentificationFile> list = new LinkedList<ValidIdentificationFile>();
        if(identificationFile1.isExist()) {
            list.add((ValidIdentificationFile)identificationFile1);
        }
        if(identificationFile2.isExist()) {
            list.add((ValidIdentificationFile)identificationFile2);
        }
        if(identificationFile3.isExist()) {
            list.add((ValidIdentificationFile)identificationFile3);
        }
        return list.toArray(new ValidIdentificationFile[0]);
    }
}
