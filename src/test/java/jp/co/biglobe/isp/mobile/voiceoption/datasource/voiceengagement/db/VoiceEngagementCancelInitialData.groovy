package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

class VoiceEngagementCancelInitialData {

    private final static Map defaultDate = [
            "cancel_reason" : "user_request",
            "cancel_sys_receipt_date_time" : "2014-01-01 00:00:00",
            "cancel_user_order_date" : "20140101",
    ]

    public final static Map firstRegistration = ["voice_engagement_number":"1"] + VoiceEngagementCancelInitialData.defaultDate
    public final static Map secondRegistration = ["voice_engagement_number":"2"] + VoiceEngagementCancelInitialData.defaultDate

}
