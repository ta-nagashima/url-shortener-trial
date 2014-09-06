package jp.co.biglobe.isp.mobile.ltethreeg.api.planchange.decision;

import jp.co.biglobe.lib.publication.validation.ValueObjectNotEmpty;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGEngagementNumberForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGServicePlanCodeForm;
import jp.co.biglobe.isp.mobile.ltethreeg.api.form.ltethreegengagement.LteThreeGServicePlanSwitchingDateForm;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlan;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString(includeFieldNames = false)
public class LteThreeGPlanChangeDecisionRequest {

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGEngagementNumberForm lteThreeGEngagementNumberForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private LteThreeGServicePlanCodeForm beforeLteThreeGServicePlanCodeForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private LteThreeGServicePlanCodeForm afterLteThreeGServicePlanCodeForm;

    @Getter
    @Setter
    @ValueObjectNotEmpty
    @Valid
    private LteThreeGServicePlanSwitchingDateForm lteThreeGServicePlanSwitchingDateForm;

    public LteThreeGServicePlan getLteThreeGServicePlan(){
        return LteThreeGServicePlanFactory.create(
                beforeLteThreeGServicePlanCodeForm.getValueObject(),
                afterLteThreeGServicePlanCodeForm.getValueObject(),
                lteThreeGServicePlanSwitchingDateForm.getValueObject()
        );
    }


}
