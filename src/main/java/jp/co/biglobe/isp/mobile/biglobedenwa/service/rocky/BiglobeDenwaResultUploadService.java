package jp.co.biglobe.isp.mobile.biglobedenwa.service.rocky;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.BiglobeDenwaResultUploadRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockyErrorFile;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockySuccessFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiglobeDenwaResultUploadService {
    @Autowired
    private BiglobeDenwaResultUploadRepository biglobeDenwaResultUploadRepository;

    public void upload(RockySuccessFile rockySuccessFile, RockyErrorFile rockyErrorFile) {

        biglobeDenwaResultUploadRepository.upload(rockySuccessFile, rockyErrorFile);
    }
}
