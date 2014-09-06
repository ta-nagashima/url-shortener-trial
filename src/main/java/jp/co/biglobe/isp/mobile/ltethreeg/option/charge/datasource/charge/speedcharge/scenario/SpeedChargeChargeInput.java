package jp.co.biglobe.isp.mobile.ltethreeg.option.charge.datasource.charge.speedcharge.scenario;

import jp.co.biglobe.isp.auth.domain.operatornouser.OperatorId;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeFee;
import jp.co.biglobe.isp.mobile.ltethreeg.option.charge.domain.charge.speedcharge.SpeedChargeItemCode;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpeedChargeChargeInput {

    // 呼び出しシナリオ名
    @Mapping("invoke_scenario_name")
    private final String invokeScenarioName = "ASPLe_createScmCharge";

    // 操作者ID
    @Mapping("sosaid")
    private OperatorId operatorId;

    // 被操作者ID
    @Mapping("hi_sosaid")
    private UserId userId;

    //商品コード
    @Mapping("shohinc")
    private SpeedChargeItemCode itemCode;

    //金額
    @Mapping("price")
    private SpeedChargeFee price;

    //課金時刻
    @Mapping("charge_time")
    private String chargeTime;



}
