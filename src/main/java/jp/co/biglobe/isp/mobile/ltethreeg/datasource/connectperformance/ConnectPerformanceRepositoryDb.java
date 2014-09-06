package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance;

import jp.co.biglobe.isp.mobile.extension.exception.SystemCheckException;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.db.ConnectPerformanceMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.limithistory.LimitHistoryRepositoryDb;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.alarm.LteThreegAlarmIdentifier;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.*;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistoryFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.ConnectPerformanceEvent;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectPerformanceRepositoryDb implements ConnectPerformanceRepository {

    @Autowired
    private ConnectPerformanceMapper connectPerformanceMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    @Autowired
    private LimitHistoryRepositoryDb limitHistoryRepositoryDb;

    @Override
    public ValidConnectPerformanceEntity findByLteThreeGEngagementNumberForValid(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        ValidConnectPerformanceEntity validConnectPerformanceEntity = connectPerformanceMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if (validConnectPerformanceEntity == null) {
            throw new SystemCheckException(
                    "データ不整合が発生しています。通信実績が存在しませんでした。", LteThreegAlarmIdentifier.DEFAULT
            );
        }

        return validConnectPerformanceEntity;
    }

    @Override
    public ConnectPerformanceEntity findByLteThreeGEngagementNumber(LteThreeGEngagementNumber lteThreeGEngagementNumber) {

        ValidConnectPerformanceEntity validConnectPerformanceEntity = connectPerformanceMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);

        if (validConnectPerformanceEntity == null) {
            return new NotExistConnectPerformanceEntity();
        }

        return validConnectPerformanceEntity;
    }

    // SIMを複数毎保有する場合、発送実績で複数回Insertのリクエストが投げられるため、存在確認後にinsertを実施する
    @Override
    public void insertMaybe(ValidConnectPerformanceEntity validConnectPerformanceEntity) {

        if( isValid(validConnectPerformanceEntity.getLteThreeGEngagementNumber()) ){
            return ;
        }

        insert(validConnectPerformanceEntity);

    }

    @Override
    public void insert(ValidConnectPerformanceEntity validConnectPerformanceEntity) {

        insertPerformance(validConnectPerformanceEntity);

        insertPolicy(validConnectPerformanceEntity);

        insertLimitHistory(validConnectPerformanceEntity, validConnectPerformanceEntity, ConnectPerformanceEvent.NEW_REGISTER);

    }


    @Override
    public void update(BeforeConnectPerformanceEntity before, AfterConnectPerformanceEntity after, ConnectPerformanceEvent connectPerformanceEvent){
        updatePerformance(after);

        updatePolicyMaybe(before, after);

        insertLimitHistory(before, after, connectPerformanceEvent);
    }

    private void insertPerformance(ValidConnectPerformanceEntity validConnectPerformanceEntity) {
        connectPerformanceMapper.insertPerformanceEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validConnectPerformanceEntity
        );
        connectPerformanceMapper.insertPerformanceState(validConnectPerformanceEntity);
    }

    private void updatePerformance(AfterConnectPerformanceEntity after){
        connectPerformanceMapper.insertPerformanceEvent(
                EventType.UPDATE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                after
        );
        connectPerformanceMapper.updatePerformanceState(after);
    }



    private void insertPolicy(ValidConnectPerformanceEntity validConnectPerformanceEntity) {
        connectPerformanceMapper.insertPolicyEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                validConnectPerformanceEntity
        );
        connectPerformanceMapper.insertPolicyState(validConnectPerformanceEntity);
    }

    private void updatePolicyMaybe(BeforeConnectPerformanceEntity before, AfterConnectPerformanceEntity after) {

        if (after.isSameConnectControlPolicy(before)) {
            return;
        }

        connectPerformanceMapper.insertPolicyEvent(
                EventType.UPDATE,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                after
        );

        connectPerformanceMapper.updatePolicyState(after);


    }

    private void insertLimitHistory(BeforeConnectPerformanceEntity before, AfterConnectPerformanceEntity after, ConnectPerformanceEvent connectPerformanceEvent) {

        LimitHistory limitHistory = LimitHistoryFactory.create(before, after, connectPerformanceEvent);
        limitHistoryRepositoryDb.register(limitHistory);

    }

    private boolean isValid(LteThreeGEngagementNumber lteThreeGEngagementNumber){

        ValidConnectPerformanceEntity validConnectPerformanceEntity = connectPerformanceMapper.findByLteThreeGEngagementNumber(lteThreeGEngagementNumber);
        if(validConnectPerformanceEntity == null){
            return false;
        }

        return true;
    }
}
