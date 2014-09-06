package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.rocky.scenario;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockyErrorFile;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockySuccessFile;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BiglobeDenwaResultUploadInput {
    @Mapping("ok_file")
    private final RockySuccessFile rockySuccessFile;
    @Mapping("ng_file")
    private final RockyErrorFile rockyErrorFile;
}
