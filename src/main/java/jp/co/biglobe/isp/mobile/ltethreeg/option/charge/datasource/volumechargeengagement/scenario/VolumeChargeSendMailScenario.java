package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.volumechargeengagement.scenario;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.volumecharge.VolumeChargeFeeFactory;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.VolumeChargePurchasedVolumeNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.volumechargeengagement.ValidVolumeChargeEngagementEntity;
import jp.co.biglobe.isp.mobile.voiceoption.datasource.voicesendmail.scenario.VoiceSendMailExceptionRule;
import jp.co.biglobe.lib.plugin.scenario.ScenarioExecutor;
import jp.co.biglobe.lib.publication.scenario.authentication.NoAuthentication;
import jp.co.biglobe.lib.publication.scenario.call.ASPFunctionCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VolumeChargeSendMailScenario {

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

    public VolumeChargeSendMailOutput send(ValidVolumeChargeEngagementEntity validVolumeChargeEngagementEntity,
                                           UserId userId,VolumeChargePurchasedVolumeNumber volumeChargePurchasedVolumeNumber) {

        VolumeChargeFee volumeChargeFee = VolumeChargeFeeFactory.create(volumeChargePurchasedVolumeNumber);

        // シナリオのインプットを作成
        VolumeChargeSendMailInput volumeChargeSendMailInput = new VolumeChargeSendMailInput(
                userId,
                validVolumeChargeEngagementEntity.getVolumeChargeOrderDateTime().getValueString(),
                validVolumeChargeEngagementEntity.getPurchasedVolumeMB().getValueSeparateComma(),
                volumeChargeFee.getValueWithTaxSeparateComma(),
                volumeChargeFee.getValueWithOutTaxSeparateComma());

        // シナリオの実行
        return scenarioExecutor.execute(
                new ASPFunctionCall(SCENARIO_NAME),
                volumeChargeSendMailInput,
                VolumeChargeSendMailOutput.class,
                new NoAuthentication(),
                new VoiceSendMailExceptionRule()
        );

    }
}
