package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db;

import jp.co.biglobe.isp.mobile.extension.datasource.CommonRegisterMapper;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.ValidMnpOut;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpout.mnpoutreservationnumber.ValidMnpOutReservationNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MnpOutNumberQueryMapper extends CommonRegisterMapper<ValidMnpOutReservationNumber> {

}
