package jp.co.biglobe.isp.mobile.voiceoption.datasource.identification.db

class IdentificationInitialData {

    private final static Map defaultDate = [
            "identification_status" : "document_waiting",
            "user_id" : "abc12345",
            "lte_three_g_engagement_number" : "00000001",
            "voice_clerk_request_status" : "requested",
    ]

    public final static Map firstRegistration = ["identification_receipt_number" : "140101_0001", "voice_engagement_number" : "1"] + IdentificationInitialData.defaultDate

    public final static Map secondRegistration = ["identification_receipt_number" : "140101_0002", "voice_engagement_number" : "2"] + IdentificationInitialData.defaultDate

}
