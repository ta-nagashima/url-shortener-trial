package jp.co.biglobe.isp.mobile.callhistory.api.history.summayrefer

import jp.co.biglobe.isp.mobile.callhistory.domain.month.TargetMonth
import jp.co.biglobe.lib.plugin.view.JsonTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallHistorySummaryReferResponseForStub {

    @Autowired
    private JsonTemplate template;

    public Map build(TargetMonth targetMonth) {

        Map map1 = [

                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234"
        ]


        Map map2 = [

                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234"
        ]


        Map map3 = [

                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234"
        ]

        Map map4 = [

                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234"
        ]

        def listDomestic = [map1, map2]
        def listInternational= [map3, map4]

        Map map5 = [
                "msisdn": "090-1234-5678",
                "total" : "20"
        ]

        Map map6 = [
                "msisdn": "090-1234-5679",
                "total" : "10"
        ]


        Map map7 = [
                "msisdn": "090-1234-5678",
                "total" : "20"
        ]

        Map map8 = [
                "msisdn": "090-1234-5679",
                "total" : "10"
        ]

        def listMsisdnDomestic = [map5, map6]
        def listMsisdnInternational = [map7, map8]

        Map map9 = [

                "id"              : "aaa11111",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234"
        ]


        Map map10 = [

                "id"              : "aaa11111",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234"
        ]


        Map map11 = [

                "id"              : "aaa11111",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234"
        ]

        Map map12 = [

                "id"              : "aaa11111",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234"
        ]

        def listFamilyIdDomestic1 = [map9, map10]
        def listFamilyIdInternational1= [map11, map12]

        Map map9_2 = [

                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234"
        ]


        Map map10_2 = [

                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234"
        ]


        Map map11_2 = [

                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234"
        ]

        Map map12_2 = [

                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234"
        ]

        def listFamilyIdDomestic2 = [map9_2, map10_2]
        def listFamilyIdInternational2 = [map11_2, map12_2]

        Map map13 = [

                "msisdn"          : "090-1234-5678",
                "id"              : "aaa11111",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]


        Map map14 = [

                "msisdn"          : "090-1234-5678",
                "id"              : "aaa11111",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]


        Map map15 = [

                "msisdn"          : "090-1234-5678",
                "id"              : "aaa11111",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]

        Map map16 = [

                "msisdn"          : "090-1234-5678",
                "id"              : "aaa11111",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]

        def listFamilyMsisdnDomestic1 = [map13, map14]
        def listFamilyMsisdnInternational1 = [map15, map16]


        Map map13_2 = [

                "msisdn"          : "090-1234-5679",
                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]


        Map map14_2 = [

                "msisdn"          : "090-1234-5679",
                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]


        Map map15_2 = [

                "msisdn"          : "090-1234-5679",
                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]

        Map map16_2 = [

                "msisdn"          : "090-1234-5679",
                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]

        def listFamilyMsisdnDomestic2 = [map13_2, map14_2]
        def listFamilyMsisdnInternational2 = [map15_2, map16_2]


        Map map13_3 = [

                "msisdn"          : "090-1234-5680",
                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（国内宛）",
                "unitPrice"        : "20円/30秒",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]


        Map map14_3 = [

                "msisdn"          : "090-1234-5680",
                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（国内）",
                "unitPrice"        : "5/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]


        Map map15_3 = [

                "msisdn"          : "090-1234-5680",
                "id"              : "bbb22222",
                "total"           : "-",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　音声通話料（海外宛）",
                "unitPrice"        : "-",
                "engagementNumber": "0001234",
                "duration"      : "0:00:29",
        ]

        Map map16_3 = [

                "msisdn"          : "090-1234-5680",
                "id"              : "bbb22222",
                "total"           : "300",
                "chargeName"      : "「ＢＩＧＬＯＢＥ　ＬＴＥ・３Ｇ」　ＳＭＳ送信料（海外）",
                "unitPrice"        : "50/通",
                "engagementNumber": "0001234",
                "duration"      : "-",
        ]

        def listFamilyMsisdnDomestic3 = [map13_3, map14_3]
        def listFamilyMsisdnInternational3 = [map15_3, map16_3]

        Map map17 = [
                "msisdn": "090-1234-5678",
                "id": "aaa11111",
                "total" : "20"
        ]

        Map map18 = [
                "msisdn": "090-1234-5679",
                "id": "bbb22222",
                "total" : "10"
        ]

        Map map18_2 = [
                "msisdn": "090-1234-5680",
                "id": "bbb22222",
                "total" : "10"
        ]

        Map map19 = [
                "msisdn": "090-1234-5678",
                "id": "aaa11111",
                "total" : "20"
        ]

        Map map20 = [
                "msisdn": "090-1234-5679",
                "id": "bbb22222",
                "total" : "10"
        ]

        Map map20_2 = [
                "msisdn": "090-1234-5680",
                "id": "bbb22222",
                "total" : "10"
        ]

        def listFamilyMsisdnMsisdnDomestic = [map17, map18, map18_2]
        def listFamilyMsisdnMsisdnInternational = [map19, map20, map20_2]

        return template.build(
                [
                        "targetMonth": targetMonth.getValue(),
                        "domestic"   : [
                                "parent"   : [
                                        "engagement": listDomestic.collect { it },
                                        "msisdn": listMsisdnDomestic.collect { it }
                                ],
                                "familyid": [
                                        ["id": "aaa11111", "engagement": listFamilyIdDomestic1.collect { it }],
                                        ["id": "bbb22222", "engagement": listFamilyIdDomestic2.collect { it }],
                                ],
                                "familymsisdn": [
                                        ["msisdn": "090-1234-5678", "engagement": listFamilyMsisdnDomestic1.collect { it }],
                                        ["msisdn": "090-1234-5679", "engagement": listFamilyMsisdnDomestic2.collect { it }],
                                        ["msisdn": "090-1234-5680", "engagement": listFamilyMsisdnDomestic3.collect { it }],
                                ],
                                "family": [
                                        "msisdn": listFamilyMsisdnMsisdnDomestic.collect { it },
                                ]
                        ],

                        "international"   : [
                                "parent"   : [
                                        "engagement": listInternational.collect { it },
                                        "msisdn": listMsisdnInternational.collect { it }
                                ],
                                "familyid": [
                                        ["engagement": listFamilyIdInternational1.collect { it }],
                                        ["engagement": listFamilyIdInternational2.collect { it }],
                                ],
                                "familymsisdn": [
                                        ["engagement": listFamilyMsisdnInternational1.collect { it }],
                                        ["engagement": listFamilyMsisdnInternational2.collect { it }],
                                        ["engagement": listFamilyMsisdnInternational3.collect { it }],
                                ],
                                "family": [
                                        "msisdn": listFamilyMsisdnMsisdnInternational.collect { it },
                                ]
                        ],
                ]
        );
    }
}