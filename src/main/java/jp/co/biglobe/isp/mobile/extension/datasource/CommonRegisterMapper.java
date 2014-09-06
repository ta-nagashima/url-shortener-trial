package jp.co.biglobe.isp.mobile.extension.datasource;

import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CommonRegisterMapper<V> {

    public void insertEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("validData") V validData
    );

    public void insertState(
            @Param("after") V after
    );

    public int updateState(
            @Param("before") V before,
            @Param("after") V after
    );

    public int deleteState(
            @Param("before") V before
    );

    public V selectState(
            @Param("before") V before
    );
}
