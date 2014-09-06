package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.registered;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common.CommonRequest;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateRegisteredService;
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
 * 登録結果反映
 *
 * - 連携結果取り込みバッチから呼ばれる
 * - waiting_register_result から registered に更新する
 */

@Controller
public class BiglobeDenwaLinkageUpdateRegisteredApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageUpdateRegisteredService service;

    @Autowired
    private UpdateApiResponse response;

    @RequestMapping(value = "/biglobedenwa/skip/linkage/update/registered/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid CommonRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        service.update(
                request.getBiglobeDenwaMsisdnForm().getValueObject()
        );

        return response.build();
    }

}
