package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.speedchargeengagement.scenario;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFeeFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.SpeedChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.speedchargeengagement.ValidSpeedChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailExceptionRule;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpeedChargeSendMailScenario {

    /**
     * シナリオ名
     *
     * 仕様書のパス
     * \\Wufei.hq.biglobe.nec.co.jp\構成管理\サービス\受注管理基盤\★最新版\06_FD-FT\01_FD\IF仕様書\(サブ-02)メール送信シナリオ Ｉ／Ｆ仕様書.xls
     */
    private static final String SCENARIO_NAME = "M_JKBS_Mail_send";

    /**
     * シナリオ実行オブジェクト
     */
    @Autowired
    ScenarioExecutor scenarioExecutor;

    public SpeedChargeSendMailOutput send(ValidSpeedChargeEngagementEntity validSpeedChargeEngagementEntity,
                                           UserId userId,SpeedChargePurchasedVolumeNumber speedChargePurchasedVolumeNumber) {

        SpeedChargeFee speedChargeFee = SpeedChargeFeeFactory.create(speedChargePurchasedVolumeNumber);

        // シナリオのインプットを作成
        SpeedChargeSendMailInput speedChargeSendMailInput = new SpeedChargeSendMailInput(
                userId,
                validSpeedChargeEngagementEntity.getSpeedChargeOrderDateTime().getValueString(),
                validSpeedChargeEngagementEntity.getPurchasedVolumeMB().getValueSeparateComma(),
                speedChargeFee.getValueWithTaxSeparateComma(),
                speedChargeFee.getValueWithOutTaxSeparateComma());

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                speedChargeSendMailInput,
                SpeedChargeSendMailOutput.class,
                new NoAuthentication(),
                new VoiceSendMailExceptionRule()
        );

    }
}
