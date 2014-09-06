package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

public interface LteThreeGServicePlanChange {

    public boolean isChangeReservation();

    public String getBeforeServicePlanCodeScenarioValue();

    public LteThreeGServicePlanSwitchingDateTime getSwitchingDateScenarioValue();
}
