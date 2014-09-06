package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.AfterBuyChargeFreeId;
import jp.co.biglobe.isp.mobile.extension.date.DateToString;
import jp.co.biglobe.isp.mobile.extension.date.SystemDateTime;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.*;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer.ConnectAuthReferExceptionRule;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCallLegm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectAuthDeleteScenario {
    /**
     * シナリオ名
     * <p/>
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\論理サブシステム\会員管理\BO顧客管理\最新版\ＡＰＩ仕様書\02T,03-商契
     * 03-1-04 商品契約更新(API).xls
     */
    private static final String SCENARIO_NAME = "BOContexp_changeGoodsContract";

    @Autowired
    ScenarioExecutor scenarioExecutor;

    public void deleteForUser(UserId userId,RiyoguchiNo riyoguchiNo,KeiyakuEdano keiyakuEdano){

        SystemDateTime systemDateTime = new SystemDateTime();
        String systemDateTimeString = DateToString.get_yyyyMMddHHmmss(systemDateTime.getValue());

        ConnectAuthDeleteUserInput connectAuthDeleteUserInput = new ConnectAuthDeleteUserInput(
                new KaiMoushikomiYmdh(systemDateTimeString), userId, userId,
                riyoguchiNo,keiyakuEdano,new KeiyakuYmdhEnd(systemDateTimeString), new KykKigenYmd(systemDateTimeString));

        // シナリオの実行
        scenarioExecutor.execute(
                new ASPFunctionCallLegm(SCENARIO_NAME),
                connectAuthDeleteUserInput,
                ConnectAuthDeleteOutput.class,
                new NoAuthentication(),
                new ConnectAuthReferExceptionRule()
                );
    }

    public void deleteForChargeFree(AfterBuyChargeFreeId afterBuyChargeFreeId, RiyoguchiNo riyoguchiNo, KeiyakuEdano keiyakuEdano){

        SystemDateTime systemDateTime = new SystemDateTime();
        String systemDateTimeString = DateToString.get_yyyyMMddHHmmss(systemDateTime.getValue());

        ConnectAuthChargeFreeDeleteInput connectAuthChargeFreeDeleteInput = new ConnectAuthChargeFreeDeleteInput(
                new KaiMoushikomiYmdh(systemDateTimeString), afterBuyChargeFreeId, afterBuyChargeFreeId,
                riyoguchiNo,keiyakuEdano,new KeiyakuYmdhEnd(systemDateTimeString), new KykKigenYmd(systemDateTimeString));

        // シナリオの実行
        scenarioExecutor.execute(
                new ASPFunctionCallLegm(SCENARIO_NAME),
                connectAuthChargeFreeDeleteInput,
                ConnectAuthDeleteOutput.class,
                new NoAuthentication(),
                new ConnectAuthReferExceptionRule()
        );
    }

}
