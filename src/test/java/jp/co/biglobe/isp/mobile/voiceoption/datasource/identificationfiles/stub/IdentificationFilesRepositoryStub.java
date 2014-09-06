package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles.stub;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IdentificationFilesRepositoryStub implements IdentificationFilesRepository {

    @Override
    public void register(IdentificationFiles identificationFiles) {

    }
}
