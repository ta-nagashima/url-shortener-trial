package jp.co.biglobe.isp.mobile.voiceoption.api.identificationdetailrefer;

import jp.co.biglobe.isp.mobile.voiceoption.domain.identification.Identification;
import jp.co.biglobe.isp.mobile.voiceoption.service.identification.IdentificationDetailReferService;
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
 * 本人確認情報参照
 */
@Controller
public class IdentificationDetailReferApi {
    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    // 本人確認参照サービスの宣言
    @Autowired
    private IdentificationDetailReferService identificationDetailReferService;

    //レスポンスの宣言
    @Autowired
    private IdentificationDetailReferResponse identificationDetailReferResponse;


    @RequestMapping(value = "/voiceoption/outside/skip/identificationdetail/refer", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid IdentificationDetailReferRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        //本人確認参照サービスのメソッドを実行する
        Identification identification = identificationDetailReferService.refer(request.getLteThreeGEngagementNumberForm().getValueObject());

        return identificationDetailReferResponse.build(identification);

    }
}
