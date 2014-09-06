package jp.co.biglobe.isp.mobile.biglobedenwa.datasource.linkage.db;

import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.ValidBiglobeDenwaLinkage;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LinkageMapper {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("validData") ValidBiglobeDenwaLinkage validData
    );

    public void insertState(
            @Param("after") ValidBiglobeDenwaLinkage after
    );

    public int updateState(
            @Param("after") ValidBiglobeDenwaLinkage after
    );

    public int deleteState(
            @Param("before") ValidBiglobeDenwaLinkage before
    );

    public ValidBiglobeDenwaLinkage findByMsisdn(
            @Param("msisdn") BiglobeDenwaMsisdn msisdn
    );

    public BiglobeDenwaMsisdn lockByMsisdn(
            @Param("msisdn") BiglobeDenwaMsisdn msisdn
    );

}
