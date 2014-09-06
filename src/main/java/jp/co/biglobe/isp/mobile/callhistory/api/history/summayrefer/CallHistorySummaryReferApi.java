package jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer;

import jp.co.biglobe.isp.auth.api.form.TotalAuthRequest;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import jp.co.biglobe.isp.auth.service.authentication.totalauth.TotalAuthService;
import jp.co.biglobe.isp.mobile.callhistory.domain.container.CallHistorySummaryReferContainer;
import jp.co.biglobe.isp.mobile.callhistory.service.SummaryReferService;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class CallHistorySummaryReferApi {

    @Autowired
    private SummaryReferService summaryReferService;

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private TotalAuthService totalAuthService;

    @Autowired
    private CallHistorySummaryReferResponse response;

    @Autowired
    private CallHistorySummaryReferResponseForStub callHistorySummaryReferResponseForStub;


    @RequestMapping(value = "/history/outside/user/summary/referstub", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(
            @Valid CallHistorySummaryReferRequest request, Errors errors) {

        // バリデーション
        alarmValidationVerifier.verify(errors);

        return callHistorySummaryReferResponseForStub.build(
                request.getTargetMonthForm().getValueObject()
        );
    }


    @RequestMapping(value = "/history/outside/user/summary/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(
            @Valid CallHistorySummaryReferRequest request, Errors errors,
            @Valid TotalAuthRequest authRequest, Errors authErrors) {

        // バリデーション
        alarmValidationVerifier.verify(errors);
        alarmValidationVerifier.verify(authErrors);

        // 統合認証
        TotalAuthResult totalAuthResult = totalAuthService.auth(authRequest.getTotalAuthForm());

        // ユーザーID（親）の取得
        ValidUserAuthTemp validSimpleBiglobeUser = (ValidUserAuthTemp) totalAuthResult.getUserAuth();
        UserId userId = validSimpleBiglobeUser.getUserId();

        // Containerオブジェクトの取得
        CallHistorySummaryReferContainer container
                = summaryReferService.refer(userId, request.getTargetMonthForm().getValueObject());


        return response.build(
                container.getTargetMonth(),
                container.getBiglobeIdSummaryList(),
                container.getMsisdnSummaryList()
        );
    }


    /**
     * 動作確認用
     */


    @RequestMapping(value = "/history/outside/user/summary/refertest", method = RequestMethod.POST)
    @ResponseBody
    public Map referForTest(
            @Valid CallHistorySummaryReferRequestForTest request, Errors errors) {

        // バリデーション
        alarmValidationVerifier.verify(errors);

        // ユーザーIDの取得
        UserId userId = request.getUserIdForm().getValueObject();


        // Containerオブジェクトの取得
        CallHistorySummaryReferContainer container
                = summaryReferService.refer(userId, request.getTargetMonthForm().getValueObject());


        return response.build(
                container.getTargetMonth(),
                container.getBiglobeIdSummaryList(),
                container.getMsisdnSummaryList()
        );
    }


}
