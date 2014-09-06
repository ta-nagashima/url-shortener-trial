package jp.co.biglobe.isp.mobile.voiceoption.datasource.mnpout.db

class MnpOutStateInitialData {

    private final static Map defaultDate = [
            "mnp_out_status" : "request_waiting",
            "cancel_reason" : "not_cancel",
    ]

    public final static Map firstRegistration = ["voice_mnp_out_id":"1", "voice_engagement_number":"1", "mnp_out_msisdn" : "090-1234-5678"] + MnpOutStateInitialData.defaultDate
    public final static Map secondRegistration = ["voice_mnp_out_id":"2", "voice_engagement_number":"2", "mnp_out_msisdn" : "090-1234-5679"] + MnpOutStateInitialData.defaultDate

}
