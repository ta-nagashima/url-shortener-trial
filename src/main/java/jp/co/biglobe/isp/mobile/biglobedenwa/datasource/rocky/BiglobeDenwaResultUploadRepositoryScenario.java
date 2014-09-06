package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.rocky;

import jp.co.biglobe.isp.mobile.biglobedenwa.datasource.rocky.scenario.BiglobeDenwaResultUploadScenario;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.BiglobeDenwaResultUploadRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockyErrorFile;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockySuccessFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BiglobeDenwaResultUploadRepositoryScenario implements BiglobeDenwaResultUploadRepository {
    @Autowired
    private BiglobeDenwaResultUploadScenario biglobeDenwaResultUploadScenario;

    @Override
    public void upload(RockySuccessFile rockySuccessFile, RockyErrorFile rockyErrorFile) {

        biglobeDenwaResultUploadScenario.update(rockySuccessFile, rockyErrorFile);
    }
}
