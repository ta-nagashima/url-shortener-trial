package jp.co.biglobe.isp.mobile.voiceoption.datasource.voiceengagement.db

class VoiceEngagementDisengageInitialData {

    private final static Map defaultDate = [
            "disengage_reason" : "user_request",
            "disengage_sys_receipt_date_t" : "2014-01-01 00:00:00",
            "disengage_user_order_date" : "20140101",
    ]

    public final static Map firstRegistration = ["voice_engagement_number":"1"] + VoiceEngagementDisengageInitialData.defaultDate
    public final static Map secondRegistration = ["voice_engagement_number":"2"] + VoiceEngagementDisengageInitialData.defaultDate

}
