package jp.co.biglobe.isp.mobile.voiceoption.datasource.identificationfiles.sftp;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFiles;
import jp.co.biglobe.isp.mobile.voiceoption.domain.identificationfiles.IdentificationFilesRepository;
import jp.co.biglobe.lib.essential.property.PropertyAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("IdentificationFilesRepositorySftp")
public class IdentificationFilesRepositorySftp implements IdentificationFilesRepository {

    @Autowired
    private PropertyAccessor propertyAccessor;

    @Override
    public void register(IdentificationFiles identificationFiles) {

    }


}
