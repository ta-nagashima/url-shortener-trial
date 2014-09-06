package jp.co.biglobe.isp.mobile.callhistory.api.history.detailrefer;

import jp.co.biglobe.isp.auth.api.form.TotalAuthRequest;
import jp.co.biglobe.isp.auth.domain.auth.totalauthresult.TotalAuthResult;
import jp.co.biglobe.isp.auth.domain.user.UserId;
import jp.co.biglobe.isp.auth.domain.user.ValidUserAuthTemp;
import jp.co.biglobe.isp.auth.service.authentication.totalauth.TotalAuthService;
import jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer.CallHistorySummaryReferResponseForStub;
import jp.co.biglobe.isp.mobile.callhistory.domain.container.CallHistoryDetailReferContainer;
import jp.co.biglobe.isp.mobile.callhistory.domain.paging.Paging;
import jp.co.biglobe.isp.mobile.callhistory.service.DetailReferService;
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
public class CallHistoryDetailReferApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private TotalAuthService totalAuthService;

    @Autowired
    private CallHistoryDetailReferResponse response;

    @Autowired
    private DetailReferService detailReferService;

    @Autowired
    private CallHistoryDetailReferResponseForStub callHistoryDetailReferResponseForStub;


    // todo : スタブ

    @RequestMapping(value = "/history/outside/user/detail/referstub", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid CallHistoryDetailReferRequest request, Errors errors) {

        // バリデーション
        alarmValidationVerifier.verify(errors);

        return callHistoryDetailReferResponseForStub.build(
                request.getDisplayCountForm().getValueObject(), request.getInternationalCallKind().getValueObject());
    }





    @RequestMapping(value = "/history/outside/user/detail/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map refer(@Valid CallHistoryDetailReferRequest requst, Errors errors,
                     @Valid TotalAuthRequest authRequest, Errors authErrors) {

        // バリデーション
        alarmValidationVerifier.verify(errors);
        alarmValidationVerifier.verify(authErrors);

        // 統合認証
        TotalAuthResult totalAuthResult = totalAuthService.auth(authRequest.getTotalAuthForm());

        // ユーザーID（親）の取得
        ValidUserAuthTemp validSimpleBiglobeUser = (ValidUserAuthTemp)totalAuthResult.getUserAuth();
        UserId userId = validSimpleBiglobeUser.getUserId();

        CallHistoryDetailReferContainer callHistoryDetailReferContainer
                = detailReferService.refer(
                userId, requst.getMsisdn().getValueObject(),
                requst.getTargetMonth().getValueObject(),
                new Paging(requst.getDisplayCountForm().getValueObject(), requst.getPageNumberForm().getValueObject()),
                requst.getInternationalCallKind().getValueObject()
        );

        return response.build(
                callHistoryDetailReferContainer,
                requst.getInternationalCallKind().getValueObject(),
                requst.getMsisdn().getValueObject()
        );
    }


    @RequestMapping(value = "/history/outside/user/detail/refertest", method = RequestMethod.POST)
    @ResponseBody
    public Map refertest(@Valid CallHistoryDetailReferRequestForTest requst, Errors errors) {

        alarmValidationVerifier.verify(errors);
        UserId userId = requst.getUserIdForm().getValueObject();

        CallHistoryDetailReferContainer callHistoryDetailReferContainer
                = detailReferService.refer(
                userId,
                requst.getMsisdn().getValueObject(),
                requst.getTargetMonth().getValueObject(),
                new Paging(requst.getDisplayCountForm().getValueObject(), requst.getPageNumberForm().getValueObject()),
                requst.getInternationalCallKind().getValueObject()
        );


        return response.build(
                callHistoryDetailReferContainer,
                requst.getInternationalCallKind().getValueObject(),
                requst.getMsisdn().getValueObject()
        );
    }
}
