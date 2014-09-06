package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.waitingregisterresult;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common.CommonRequest;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateWaitingRegisterResultService;
import jp.co.biglobe.lib.plugin.response.normal.UpdateApiResponse;
import jp.co.biglobe.lib.plugin.validationverifier.AlarmValidationVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * 連携番号登録
 *
 * - 登録連携抽出バッチから呼ばれる
 * - waiting_register から waiting_register_resultに更新する
 */

@Controller
public class BiglobeDenwaLinkageUpdateWaitingRegisterResultApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageUpdateWaitingRegisterResultService service;

    @Autowired
    private UpdateApiResponse response;

    @RequestMapping(value = "/biglobedenwa/skip/linkage/update/waitingregisterresult/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid CommonRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        service.update(
                request.getBiglobeDenwaMsisdnForm().getValueObject()
        );

        return response.build();
    }

}
