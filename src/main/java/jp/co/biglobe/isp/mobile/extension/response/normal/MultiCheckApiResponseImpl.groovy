package jp.co.biglobe.isp.mobile.extension.response.normal

import jp.co.biglobe.lib.plugin.view.JsonTemplate
import jp.co.biglobe.lib.publication.enumeration.businessstatus.BusinessStatusForApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 複数チェック系APIのデフォルトレスポンス
 */
@Component
public class MultiCheckApiResponseImpl implements MultiCheckApiResponse {

    @Autowired
    private JsonTemplate template;

    @Override
    public Map build(Map businessStatusMap){
        template.build(buildBusinessStatusMap(businessStatusMap));
    }

    @Override
    public Map build(Map businessStatusMap, Map body){
        template.build(buildBusinessStatusMap(businessStatusMap) + body);
    }

    private Map buildBusinessStatusMap(Map businessStatusMap){
        Map result = new HashMap<String, Map>();
        for (Map.Entry<String, BusinessStatusForApi> e: businessStatusMap.entrySet()) {
            result.put(e.getKey(), buildCheckApiFixationData(e.getValue()));
        }

        return result;
    }

    /**
     * lib.componentパッケージ配下のCheckApiFixationDataの処理を一旦コピペ
     *
     * 将来的にMultiCheckApiResponseをlibパッケージに昇格させる時に、本メソッドは削除し、
     * CheckApiFixationDataを使用するように変更する。
     *
     * 現時点では、MultiCheckApiResponseはアプリケーションのextensionパッケージ配下にあり
     * lib.componentパッケージに依存するのは好ましくないため、このような実装としている。
     *
     * @deprecated
     */
    private Map buildCheckApiFixationData(BusinessStatusForApi businessStatusForApi){
        Map fixationData = [
                "result" : businessStatusForApi.getCheckable().getApiValue(),
                "detail" : [
                        "type" : businessStatusForApi.getApiValue(),
                        "message" : businessStatusForApi.getMessage(),
                ],
        ]

        return fixationData
    }
}
