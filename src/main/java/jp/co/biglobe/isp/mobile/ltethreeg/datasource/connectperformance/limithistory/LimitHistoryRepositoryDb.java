package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.limithistory;

import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectperformance.limithistory.db.LimitHistoryMapper;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectperformance.limithistory.LimitHistory;
import jp.co.biglobe.lib.plugin.event.RequestEventProcess;
import jp.co.biglobe.lib.plugin.event.RequestEventTime;
import jp.co.biglobe.lib.publication.datasource.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LimitHistoryRepositoryDb{

    @Autowired
    private LimitHistoryMapper limitHistoryMapper;

    @Autowired
    private RequestEventTime requestEventTime;

    @Autowired
    private RequestEventProcess requestEventProcess;

    public void register(LimitHistory limitHistory) {

        limitHistoryMapper.insertEvent(
                EventType.INSERT,
                requestEventTime.getDate(),
                requestEventProcess.getValue(),
                limitHistory
        );

    }
}
