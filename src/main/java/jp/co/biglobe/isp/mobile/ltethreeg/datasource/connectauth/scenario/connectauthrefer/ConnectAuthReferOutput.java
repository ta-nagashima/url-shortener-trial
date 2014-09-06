package jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthrefer;

import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.KeiyakuEdano;
import jp.co.biglobe.isp.mobile.ltethreeg.datasource.connectauth.scenario.connectauthdelete.scenarioparam.RiyoguchiNo;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.connectauth.bandcontroldevice.BandControlDevice;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.LteThreeGEngagementNumber;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanCode;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.ltethreegengagement.serviceplan.LteThreeGServicePlanSwitchingDate;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.Msisdn;
import jp.co.biglobe.isp.mobile.ltethreeg.domain.sim.ValidMsisdn;
import jp.co.biglobe.lib.publication.scenario.annotation.Mapping;
import jp.co.biglobe.lib.publication.scenario.bobio.BobioHeader;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectAuthReferOutput {

    @Getter
    private BobioHeader bobioHeader;

    @Mapping("sosaid_out")
    @Getter
    private UserId userId;

    @Mapping("apsv_own_info[1]")
    @Getter
    private ValidMsisdn validMsisdn;

    @Mapping("hanyo_info1[1]")
    @Getter
    private LteThreeGServicePlanCode lteThreeGServicePlanCodeAfter;

    @Mapping("hanyo_info2[1]")
    @Getter
    private LteThreeGServicePlanCode lteThreeGServicePlanCodeBefore;

    @Mapping("hanyo_info3[1]")
    @Getter
    private LteThreeGServicePlanSwitchingDate lteThreeGServicePlanSwitchingDate;

    @Mapping("hanyo_info4[1]")
    @Getter
    private String connectControlPolicy;


    @Mapping("hanyo_info6[1]")
    @Getter
    private LteThreeGEngagementNumber lteThreeGEngagementNumber;

    @Mapping("keiyaku_ymdh_end[1]")
    @Getter
    private String lteThreeGConnectAuthEndDateTime;

    @Mapping("riyoguchi_no[1]")
    @Getter
    private RiyoguchiNo riyoguchiNo;

    @Mapping("ryg_optkyk_edano[1]")
    @Getter
    private KeiyakuEdano keiyakuEdano;


}
