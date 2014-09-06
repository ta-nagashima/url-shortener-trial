package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.waitingremove;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common.CommonRequest;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateWaitingRemoveService;
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
 * 退会連動
 * <p>
 * - 退会連動バッチから呼ばれる
 * - registered から waiting_removeに更新する
 */

@Controller
public class BiglobeDenwaLinkageUpdateWaitingRemoveApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageUpdateWaitingRemoveService service;

    @Autowired
    private UpdateApiResponse response;

    @RequestMapping(value = "/biglobedenwa/skip/linkage/update/waitingremove/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid CommonRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        service.update(
                request.getBiglobeDenwaMsisdnForm().getValueObject()
        );

        return response.build();
    }

}
