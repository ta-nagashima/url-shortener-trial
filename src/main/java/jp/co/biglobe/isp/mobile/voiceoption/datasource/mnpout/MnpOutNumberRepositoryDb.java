package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout;

import jp.co.biglobe.isp.mobile.extension.datasource.BaseRepositoryDb;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.MnpOutReservationNumber;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
import org.springframework.stereotype.Repository;

@Repository
public class MnpOutNumberRepositoryDb extends BaseRepositoryDb<MnpOutReservationNumber, ValidMnpOutReservationNumber> {
    @Override
    protected void childInsert(ValidMnpOutReservationNumber after) {

    }

    @Override
    protected void childDelete(ValidMnpOutReservationNumber before) {

    }

    @Override
    protected boolean childDelete(ValidMnpOutReservationNumber before, ValidMnpOutReservationNumber after) {
        return false;
    }

    @Override
    protected void childInsertOrUpdate(ValidMnpOutReservationNumber before, ValidMnpOutReservationNumber after) {

    }
}
