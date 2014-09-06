package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

class VoiceEngagementInitialData {

    private final static Map defaultDate = [
            "system_receipt_date_time" : "2014-01-01 00:00:00",
            "voice_engagement_status" : "ordered",
            "voice_user_order_date" : "20140101",
            "engagement_start_date" : "29991231",
            "engagement_end_date_time" : "2999-12-31 23:59:59",
            "voice_join_code" : "webyn002",
            "voice_cancel_list_op_status" : "not_output",
            "user_id" : "abc12345",
            "lte_three_g_engagement_number" : "00000001",
            "voice_order_type" : "order_with_lte"
    ]

    public final static Map firstRegistration = ["voice_engagement_number":"1", "equipment_number" : "00000001"] + VoiceEngagementInitialData.defaultDate
    public final static Map secondRegistration = ["voice_engagement_number":"2", "equipment_number" : "00000002"] + VoiceEngagementInitialData.defaultDate

}
