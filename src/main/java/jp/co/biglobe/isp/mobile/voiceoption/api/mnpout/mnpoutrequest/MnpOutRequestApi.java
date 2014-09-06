package jp.co.biglobe.isp.mobile.voiceoption.api.mnpout.mnpoutrequest;

import jp.co.biglobe.isp.mobile.voiceoption.domain.container.MnpOutRequestInfoReturnContainer;
import jp.co.biglobe.isp.mobile.voiceoption.service.mnpout.MnpOutRequestService;
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
 * MNP転出依頼
 *
 * バッチから呼ばれる
 * 転出申込の後に転出状態を REQUEST_WAITING から MNP_RESERVATION_NUMBER_WAITINIG に変更する
 */

@Controller
public class MnpOutRequestApi {

    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpOutRequestService mnpOutRequestService;

    @Autowired
    private MnpOutRequestResponse mnpOutRequestResponse;

    @RequestMapping(value = "/voiceoption/skip/mnpoutrequest/submit", method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid MnpOutRequestRequest mnpOutRequestRequest, Errors errors) {

        alarmValidationVerifier.verify(errors);

        // 転出情報の更新と事務局への出力
        MnpOutRequestInfoReturnContainer mnpOutRequestInfoReturnContainer =
                mnpOutRequestService.update(mnpOutRequestRequest.getVoiceEngagementNumberForm().getValueObject());

        return mnpOutRequestResponse.build(mnpOutRequestInfoReturnContainer);
    }

}
