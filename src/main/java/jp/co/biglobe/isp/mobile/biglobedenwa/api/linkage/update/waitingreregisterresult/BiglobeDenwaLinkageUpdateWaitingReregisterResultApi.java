package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.waitingreregisterresult;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common.CommonRequest;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateWaitingReregisterResultService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.mnpin.MnpInExistReferService;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
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
 * 再登録連携
 * <p>
 * - 登録連携抽出バッチから呼ばれる
 * - waiting_reregister から waiting_reregister_resultに更新する
 */

@Controller
public class BiglobeDenwaLinkageUpdateWaitingReregisterResultApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private BiglobeDenwaLinkageUpdateWaitingReregisterResultService service;

    @Autowired
    private MnpInExistReferService mnpInExistReferService;

    @Autowired
    private BiglobeDenwaLinkageUpdateWaitingReregisterResultResponse response;

    @RequestMapping(value = "/biglobedenwa/skip/linkage/update/waitingreregisterresult/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid CommonRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        service.update(
                request.getBiglobeDenwaMsisdnForm().getValueObject()
        );

        MnpIn mnpIn = mnpInExistReferService.refer(request.getBiglobeDenwaMsisdnForm().getValueObject());

        return response.build(mnpIn);
    }

}
