package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.db;

import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.AfterConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ValidConnectPerformanceEntity;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ConnectPerformanceMapper{

    public ValidConnectPerformanceEntity findByLteThreeGEngagementNumber(@Param("lteThreeGEngagementNumber") LteThreeGEngagementNumber lteThreeGEngagementNumber);

    public void insertPerformanceEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("connectPerformanceEntity") AfterConnectPerformanceEntity connectPerformanceEntity);

    public void insertPerformanceState(
            @Param("connectPerformanceEntity") ValidConnectPerformanceEntity validConnectPerformanceEntity);

    public void updatePerformanceState(
            @Param("connectPerformanceEntity") AfterConnectPerformanceEntity connectPerformanceEntity);

    public void insertPolicyEvent(
            @Param("eventType") EventType eventType,
            @Param("eventDate") Date eventDate,
            @Param("eventProcess") String eventProcess,
            @Param("connectPerformanceEntity") AfterConnectPerformanceEntity connectPerformanceEntity);

    public void insertPolicyState(@Param("connectPerformanceEntity") ValidConnectPerformanceEntity validConnectPerformanceEntity);

    public void updatePolicyState(@Param("connectPerformanceEntity") AfterConnectPerformanceEntity connectPerformanceEntity);

}
