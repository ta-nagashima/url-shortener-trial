package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.rocky.scenario;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockyErrorFile;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.rocky.RockySuccessFile;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BiglobeDenwaResultUploadScenario {
    private static final String SCENARIO_NAME = "C_BGDW_FileRecvJob_populate";

    @Autowired
    private ScenarioExecutor scenarioExecutor;

    public BiglobeDenwaResultUploadOutput update(RockySuccessFile rockySuccessFile, RockyErrorFile rockyErrorFile) {
        BiglobeDenwaResultUploadInput biglobeDenwaResultUploadInput = new BiglobeDenwaResultUploadInput(rockySuccessFile, rockyErrorFile);

        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                biglobeDenwaResultUploadInput,
                BiglobeDenwaResultUploadOutput.class,
                new NoAuthentication(),
                new BiglobeDenwaResultUploadExceptionRule()
        );
    }

}
