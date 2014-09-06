package jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.unabletoregister;

import jp.co.biglobe.isp.mobile.biglobedenwa.api.linkage.update.common.CommonRequest;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkage;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaLinkageRepository;
import jp.co.biglobe.isp.mobile.biglobedenwa.domain.linkage.BiglobeDenwaMsisdn;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUnableToRegisterService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.linkage.BiglobeDenwaLinkageUpdateWaitingReregisterService;
import jp.co.biglobe.isp.mobile.biglobedenwa.service.mnpin.MnpInExistReferService;
import jp.co.biglobe.isp.mobile.voiceoption.domain.mnpin.MnpIn;
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
 * 登録NG・再登録連携前更新
 * - 連携結果取り込みバッチから呼ばれる
 * - MnpInの有無を参照して、waiting_reregisterかunable_to_registerのどちらかにステータスを更新する
 *  - MNP転入：登録NG
 *  - 新規：再登録連携前
 * - ７、８月に音声契約したお客様は、７、８月にG-Callなどの他社サービスを申し込んだ可能性があるので、
 *   MNPの有無にかかわらず、再登録連携してはいけない
 * - ７、８月に音声契約したお客様は、アプリからの経路でしか申し込めないので、アプリから申し込んだお客様は
 *   問答無用でNGに落とす（発送管理との連携バッチは9/1以降を抜き出す条件にしているので問題なし）
 */

@Controller
public class BiglobeDenwaLinkageUpdateUnableToRegisterApi {


    @Autowired
    private AlarmValidationVerifier alarmValidationVerifier;

    @Autowired
    private MnpInExistReferService mnpInExistReferService;

    @Autowired
    private BiglobeDenwaLinkageUpdateWaitingReregisterService updateWaitingReregisterService;

    @Autowired
    private BiglobeDenwaLinkageUnableToRegisterService updateUnableToRegisterService;

    @Autowired
    private UpdateApiResponse response;


    @Autowired
    private BiglobeDenwaLinkageRepository linkageRepository;

    @RequestMapping(
            value = "/biglobedenwa/skip/linkage/update/unabletoregister/submit",
            method = RequestMethod.POST)
    @ResponseBody
    public Map invoke(@Valid CommonRequest request, Errors errors) {

        alarmValidationVerifier.verify(errors);

        BiglobeDenwaMsisdn biglobeDenwaMsisdn = request.getBiglobeDenwaMsisdnForm().getValueObject();

        MnpIn mnpIn = mnpInExistReferService.refer(biglobeDenwaMsisdn);



        /**
         * アプリからの申し込みのときはNGにする
         * todo : とりあえずSTに間に合わせるためにここで分岐を入れた。後ほどサービス層を見直して、この分岐をドメイン層に押し込む（土井）
         */

        BiglobeDenwaLinkage before = linkageRepository.findByMsisdnForUpdate(biglobeDenwaMsisdn);

        if(before.isApplyFromApp()){

            updateUnableToRegisterService.update(biglobeDenwaMsisdn);

            return response.build();

        }

        /**
         * MNP転入がない場合は、同番号の前の所有者が登録しているので再登録する。
         */

        if(mnpIn.isNotExist()) {
            updateWaitingReregisterService.update(biglobeDenwaMsisdn);

            return response.build();
        }

        /**
         * MNP転入がある場合は、申込者が既に個別に契約している可能性があるので、unable_to_registerにする。
         */

        updateUnableToRegisterService.update(biglobeDenwaMsisdn);

        return response.build();
    }

}
