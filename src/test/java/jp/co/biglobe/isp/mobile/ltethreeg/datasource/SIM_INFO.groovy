package jp.co.biglobe.isp.mobile.ltethreeg.datasource

class SIM_INFO {

    public final static Map regist = [

            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-6789-6789",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0"
                    ],

                    2 : [
                            "PRIMARY_KEY1" : "00000002",
                            "FOREIGN_KEY1" : "00000002",
                            "ITEM_FREE21" : "090-9999-9999",
                            "ITEM_FREE2" : "0",
                            "ITEM_FREE3" : "0",
                            "ITEM_FREE_DATE6" : "1999-12-31 23:59:59"
                    ],

            ],
    ]

    public final static Map data_registed = [

            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-1234-5678",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0",
                            "ITEM_FREE11" : "UIM(LTE)"
                    ]
            ]
    ]

    public final static Map voice_registed = [

            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-1234-5678",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0",
                            "ITEM_FREE11" : "SIM(LTE)-Vo"
                    ],
                    2 : [
                            "PRIMARY_KEY1" : "00000002",
                            "FOREIGN_KEY1" : "00000002",
                            "ITEM_FREE21" : "090-2345-6789",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0",
                            "ITEM_FREE11" : "SIM(LTE)-Vo"
                    ]
            ]
    ]

    public final static Map lte = [
            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-1234-1234",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0"
                    ]
            ]
    ]

    public final static Map lte_cancelled = [
            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-1234-1234",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0"
                    ]
            ]
    ]

    public final static Map lte_removed = [
            "LTE_USABLE_EQUIP" : [
                    1 : [
                            "PRIMARY_KEY1" : "00000001",
                            "FOREIGN_KEY1" : "00000001",
                            "ITEM_FREE21" : "090-1234-1234",
                            "ITEM_FREE2" : "1",
                            "ITEM_FREE3" : "0"
                    ]
            ]
    ]

}

