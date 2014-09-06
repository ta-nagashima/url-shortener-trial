package jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan;

public class LteThreeGServicePlanFactory {

    public static LteThreeGServicePlan create(
            LteThreeGServicePlanCode before,
            LteThreeGServicePlanCode after,
            LteThreeGServicePlanSwitchingDate lteThreeGServicePlanSwitchingDate) {

        if (lteThreeGServicePlanSwitchingDate == null || lteThreeGServicePlanSwitchingDate.isNotSet()) {
            return new LteThreeGServicePlan(new NotExistLteThreeGServicePlanChange(), after);
        }

        if (before == null) {
            return new LteThreeGServicePlan(new NotExistLteThreeGServicePlanChange(), after);
        }

        return new LteThreeGServicePlan(
                new ValidLteThreeGServicePlanChange(before, lteThreeGServicePlanSwitchingDate),
                after
        );
    }


    public static LteThreeGServicePlan createForAfterOnly(LteThreeGServicePlanCode after) {
        return new LteThreeGServicePlan(
                new NotExistLteThreeGServicePlanChange(),
                after
        );
    }
}
