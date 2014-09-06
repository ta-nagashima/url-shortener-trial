package jp.co.biglobe.isp.mobile.callhistory.api.history.detailrefer

import jp.co.biglobe.isp.mobile.callhistory.domain.chargeitem.CallKind
import jp.co.biglobe.isp.mobile.callhistory.domain.paging.DisplayCount
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallHistoryDetailReferResponseForStub {

    @Autowired
    private JsonTemplate template;

    public Map build(DisplayCount displayCount, CallKind internationalCallKind) {

        Map map;

        List<Map> listMap = new ArrayList<Map>();

        if(internationalCallKind == CallKind.DOMESTIC) {

            map = [
                    "taxStatus"     : "tax_exempt",
                    "startDateTime" : "2014/07/02 10:20:25",
                    "numberDialed"  : "090-1234-5678",
                    "communicationMethod":"音声通話",
                    "chargeItemName": "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                    "duration"      : "0:00:29",
                    "charge"        : "20"
            ]


            for (int i = 0; i < displayCount.getValue(); i++) {

                listMap.add(map);
            }
        }

        if(internationalCallKind == CallKind.INTERNATIONAL) {

            map = [
                    "taxStatus"     : "tax_free",
                    "startDateTime" : "2014/07/02 10:20:25",
                    "numberDialed"  : "090-1234-5678",
                    "communicationMethod":"音声通話",
                    "chargeItemName": "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                    "duration"      : "0:00:29",
                    "charge"        : "20",
                    "connectCountry" : "アメリカ合衆国"
            ]


            for (int i = 0; i < displayCount.getValue(); i++) {

                listMap.add(map);
            }
        }


        return template.build(
                [
                        "totalCount": "3500",
                        "history" : listMap.collect {
                            map
                        }

                ]
        );
    }

}
